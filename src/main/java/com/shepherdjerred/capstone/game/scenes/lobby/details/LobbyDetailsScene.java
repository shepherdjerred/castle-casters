package com.shepherdjerred.capstone.game.scenes.lobby.details;

import com.shepherdjerred.capstone.common.lobby.Lobby;
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
import com.shepherdjerred.capstone.game.event.events.StartGameEvent;
import com.shepherdjerred.capstone.game.event.events.TryStartGameEvent;
import com.shepherdjerred.capstone.game.network.manager.event.ShutdownNetworkEvent;
import com.shepherdjerred.capstone.game.objects.background.parallax.ParallaxBackground;
import com.shepherdjerred.capstone.game.objects.button.Button.Type;
import com.shepherdjerred.capstone.game.objects.text.Text;
import com.shepherdjerred.capstone.game.objects.textbutton.TextButton;
import com.shepherdjerred.capstone.game.scenes.game.GameScene;
import com.shepherdjerred.capstone.game.scenes.lobby.host.SimpleSceneRenderer;
import com.shepherdjerred.capstone.game.scenes.mainmenu.MainMenuScene;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashSet;
import java.util.Set;

public class LobbyDetailsScene extends InteractableUIScene {

  private final boolean isHost;
  private QuoridorPlayer player;
  private Lobby lobby;
  private final EventBus<Event> eventBus;
  private final EventHandlerFrame<Event> eventHandlerFrame;

  public LobbyDetailsScene(EventBus<Event> eventBus,
      ResourceManager resourceManager,
      WindowSize windowSize,
      QuoridorPlayer player,
      Lobby lobby,
      boolean isHost) {
    super(windowSize,
        resourceManager,
        new SimpleSceneRenderer(resourceManager, windowSize),
        eventBus);
    this.eventBus = eventBus;
    this.eventHandlerFrame = new EventHandlerFrame<>();
    this.lobby = lobby;
    this.isHost = isHost;
    this.player = player;
    createEventHandlerFrame();
    createGameObjects();
  }

  private void createEventHandlerFrame() {
    eventHandlerFrame.registerHandler(StartGameEvent.class, (event) -> {
      var lobbySettings = lobby.getLobbySettings();
      var matchSettings = lobbySettings.getMatchSettings();
      var map = lobbySettings.getGameMap();
      var boardSettings = new BoardSettings(map.getBoardSize(), matchSettings.getPlayerCount());
      var match = Match.from(matchSettings, boardSettings);

      var scene = new GameScene(resourceManager,
          eventBus,
          GameMapName.random(),
          match,
          player,
          windowSize);
      eventBus.dispatch(new SceneTransitionEvent(scene));
    });
  }

  protected Set<GameObject> createGameObjects() {
    Set<GameObject> gameObjects = new HashSet<>();
    var text = new Text(resourceManager,
        "Lobby",
        FontName.M5X7,
        Color.white(),
        24,
        100,
        new WindowRelativeScenePositioner(
            HorizontalPosition.CENTER, VerticalPosition.TOP, new SceneCoordinateOffset(0, 100), 1));

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
          eventBus.dispatch(new SceneTransitionEvent(scene));
          eventBus.dispatch(new ShutdownNetworkEvent());
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
          eventBus.dispatch(new TryStartGameEvent());
        });

    // TODO handle name updates
    var lobbyName = new Text(resourceManager,
        lobby.getLobbySettings().getName(),
        FontName.M5X7,
        Color.white(),
        24,
        300,
        new ObjectRelativeScenePositioner(text, new SceneCoordinateOffset(0, 75), 1));

    var lobbyDump = new Text(resourceManager,
        lobby.toString(),
        FontName.M5X7,
        Color.white(),
        24,
        300,
        new ObjectRelativeScenePositioner(lobbyName, new SceneCoordinateOffset(0, 75), 1));

    background = new ParallaxBackground(resourceManager, windowSize,
        ParallaxBackground.Type.random());

    gameObjects.add(lobbyName);
    gameObjects.add(lobbyDump);
    gameObjects.add(text);
    gameObjects.add(backButton);
    if (isHost) {
      gameObjects.add(nextButton);
    }
    return gameObjects;
  }

  @Override
  public void updateState(float interval) {
    super.updateState(interval);
  }

  @Override
  public void initialize() throws Exception {
    super.initialize();
    eventBus.registerHandlerFrame(eventHandlerFrame);
  }

  @Override
  public void cleanup() {
    super.cleanup();
    eventBus.removeHandlerFrame(eventHandlerFrame);
  }
}
