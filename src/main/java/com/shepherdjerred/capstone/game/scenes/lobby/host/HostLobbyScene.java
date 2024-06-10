package com.shepherdjerred.capstone.game.scenes.lobby.host;

import com.shepherdjerred.capstone.common.Constants;
import com.shepherdjerred.capstone.common.GameMap;
import com.shepherdjerred.capstone.common.GameState;
import com.shepherdjerred.capstone.common.chat.ChatHistory;
import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.common.lobby.LobbySettings;
import com.shepherdjerred.capstone.common.lobby.LobbySettings.LobbyType;
import com.shepherdjerred.capstone.engine.events.scene.SceneTransitionEvent;
import com.shepherdjerred.capstone.engine.graphics.Color;
import com.shepherdjerred.capstone.engine.graphics.font.FontName;
import com.shepherdjerred.capstone.engine.map.GameMapName;
import com.shepherdjerred.capstone.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.scene.InteractableUIScene;
import com.shepherdjerred.capstone.engine.scene.position.ObjectRelativeScenePositioner;
import com.shepherdjerred.capstone.engine.scene.position.SceneCoordinateOffset;
import com.shepherdjerred.capstone.engine.scene.position.WindowRelativeScenePositioner;
import com.shepherdjerred.capstone.engine.scene.position.WindowRelativeScenePositioner.HorizontalPosition;
import com.shepherdjerred.capstone.engine.scene.position.WindowRelativeScenePositioner.VerticalPosition;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.game.event.events.FillSlotsWithAiEvent;
import com.shepherdjerred.capstone.game.network.event.ServerConnectedEvent;
import com.shepherdjerred.capstone.game.network.manager.event.ConnectServerEvent;
import com.shepherdjerred.capstone.game.network.manager.event.ShutdownNetworkEvent;
import com.shepherdjerred.capstone.game.network.manager.event.StartClientEvent;
import com.shepherdjerred.capstone.game.network.manager.event.StartServerEvent;
import com.shepherdjerred.capstone.game.objects.background.parallax.ParallaxBackground;
import com.shepherdjerred.capstone.game.objects.button.Button.Type;
import com.shepherdjerred.capstone.game.objects.text.Text;
import com.shepherdjerred.capstone.game.objects.textbutton.TextButton;
import com.shepherdjerred.capstone.game.scenes.lobby.details.LobbyDetailsScene;
import com.shepherdjerred.capstone.game.scenes.mainmenu.MainMenuScene;
import com.shepherdjerred.capstone.logic.match.MatchSettings;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.extern.log4j.Log4j2;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

@Log4j2
public class HostLobbyScene extends InteractableUIScene {

  private final LobbySettings lobbySettings;
  private final GameMapName mapName = GameMapName.GRASS;
  private boolean isServerStarting;

  public HostLobbyScene(EventBus<Event> eventBus,
                        ResourceManager resourceManager,
                        WindowSize windowSize,
                        LobbyType lobbyType) {
    super(windowSize,
        resourceManager,
        new com.shepherdjerred.capstone.game.scenes.lobby.host.SimpleSceneRenderer(
            resourceManager,
            windowSize),
        eventBus);
    lobbySettings = new LobbySettings("My Lobby",
        new MatchSettings(10, QuoridorPlayer.ONE, PlayerCount.TWO),
        lobbyType,
        false,
        GameMap.GRASS);
  }

  protected Set<GameObject> createGameObjects() {
    Set<GameObject> gameObjects = new HashSet<>();

    var title = new Text(resourceManager,
        "Lobby Setup",
        FontName.M5X7,
        Color.white(),
        12,
        200,
        new WindowRelativeScenePositioner(HorizontalPosition.CENTER,
            VerticalPosition.TOP,
            new SceneCoordinateOffset(0, 100),
            0));

    var lobbyDump = new Text(resourceManager,
        lobbySettings.toString(),
        FontName.M5X7,
        Color.white(),
        12,
        300,
        new ObjectRelativeScenePositioner(title, new SceneCoordinateOffset(0, 75), 1));

    var backButton = new TextButton(resourceManager,
        windowSize,
        new WindowRelativeScenePositioner(HorizontalPosition.LEFT,
            VerticalPosition.BOTTOM,
            new SceneCoordinateOffset(100, -100),
            1),
        "Back",
        FontName.M5X7,
        Color.white(),
        24,
        new SceneObjectDimensions(100, 50),
        Type.GENERIC,
        () -> {
          var scene = new MainMenuScene(resourceManager,
              eventBus,
              windowSize);
          eventBus.dispatch(new ShutdownNetworkEvent());
          eventBus.dispatch(new SceneTransitionEvent(scene));
        });

    var nextButton = new TextButton(resourceManager,
        windowSize,
        new WindowRelativeScenePositioner(HorizontalPosition.RIGHT,
            VerticalPosition.BOTTOM,
            new SceneCoordinateOffset(-100, -100),
            1),
        "Next",
        FontName.M5X7,
        Color.white(),
        24,
        new SceneObjectDimensions(100, 50),
        Type.GENERIC,
        () -> {
          if (!isServerStarting) {
            isServerStarting = true;

            eventBus.dispatch(new StartServerEvent(createGameState()));

            // TODO wait for successful server creation
            eventBus.dispatch(new StartClientEvent());
            eventBus.dispatch(new ConnectServerEvent(new InetSocketAddress(Constants.GAME_PORT)));

            eventBus.registerHandler(ServerConnectedEvent.class, (event) -> {
              if (lobbySettings.lobbyType().equals(LobbyType.LOCAL)) {
                eventBus.dispatch(new FillSlotsWithAiEvent());
              }

              var scene = new LobbyDetailsScene(eventBus,
                  resourceManager,
                  windowSize,
                  QuoridorPlayer.ONE,
                  Lobby.from(lobbySettings),
                  true);
              eventBus.dispatch(new SceneTransitionEvent(scene));
            });
          }
        });

    background = new ParallaxBackground(resourceManager, windowSize,
        ParallaxBackground.Type.random());

    gameObjects.add(lobbyDump);
    gameObjects.add(title);
    gameObjects.add(backButton);
    gameObjects.add(nextButton);
    return gameObjects;
  }

  private GameState createGameState() {
    return new GameState(Lobby.from(lobbySettings), null, new ChatHistory());
  }
}
