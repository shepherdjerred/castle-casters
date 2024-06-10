package com.shepherdjerred.capstone.engine.game.scenes.mainmenu;

import static com.shepherdjerred.capstone.engine.game.objects.button.Button.Type.GENERIC;

import com.shepherdjerred.capstone.common.lobby.LobbySettings.LobbyType;
import com.shepherdjerred.capstone.engine.engine.events.CloseApplicationEvent;
import com.shepherdjerred.capstone.engine.engine.events.scene.SceneTransitionEvent;
import com.shepherdjerred.capstone.engine.engine.graphics.Color;
import com.shepherdjerred.capstone.engine.engine.graphics.font.FontName;
import com.shepherdjerred.capstone.engine.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.engine.scene.InteractableUIScene;
import com.shepherdjerred.capstone.engine.engine.scene.position.ObjectRelativeScenePositioner;
import com.shepherdjerred.capstone.engine.engine.scene.position.SceneCoordinateOffset;
import com.shepherdjerred.capstone.engine.engine.scene.position.WindowRelativeScenePositioner;
import com.shepherdjerred.capstone.engine.engine.scene.position.WindowRelativeScenePositioner.HorizontalPosition;
import com.shepherdjerred.capstone.engine.engine.scene.position.WindowRelativeScenePositioner.VerticalPosition;
import com.shepherdjerred.capstone.engine.engine.window.WindowSize;
import com.shepherdjerred.capstone.engine.game.objects.background.parallax.ParallaxBackground;
import com.shepherdjerred.capstone.engine.game.objects.background.parallax.ParallaxBackground.Type;
import com.shepherdjerred.capstone.engine.game.objects.logo.Logo;
import com.shepherdjerred.capstone.engine.game.objects.text.Text;
import com.shepherdjerred.capstone.engine.game.objects.textbutton.TextButton;
import com.shepherdjerred.capstone.engine.game.scenes.help.HelpScene;
import com.shepherdjerred.capstone.engine.game.scenes.lobby.host.HostLobbyScene;
import com.shepherdjerred.capstone.engine.game.scenes.lobby.host.SimpleSceneRenderer;
import com.shepherdjerred.capstone.engine.game.scenes.lobby.list.LobbyListScene;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MainMenuScene extends InteractableUIScene {

  private final ResourceManager resourceManager;
  private final WindowSize windowSize;
  private final MainMenuAudio sceneAudio;

  public MainMenuScene(ResourceManager resourceManager,
      EventBus<Event> eventBus,
      WindowSize windowSize) {
    super(windowSize,
        resourceManager,
        new SimpleSceneRenderer(resourceManager, windowSize),
        eventBus);
    this.resourceManager = resourceManager;
    this.windowSize = windowSize;
    this.sceneAudio = new MainMenuAudio(eventBus, resourceManager);
  }

  protected Set<GameObject> createGameObjects() {
    Set<GameObject> gameObjects = new HashSet<>();

    var logo = new Logo(resourceManager,
        new WindowRelativeScenePositioner(HorizontalPosition.CENTER,
            VerticalPosition.TOP,
            new SceneCoordinateOffset(0, 50),
            0),
        1.485517919,
        200,
        Logo.Type.GAME);

    background = new ParallaxBackground(resourceManager, windowSize,
        Type.random());

    var text = new Text(resourceManager,
        "Castle Casters",
        FontName.M5X7,
        Color.white(),
        12,
        400,
        new WindowRelativeScenePositioner(HorizontalPosition.RIGHT,
            VerticalPosition.BOTTOM,
            new SceneCoordinateOffset(-10, -10),
            1)
    );

    var buttonSize = new SceneObjectDimensions(200, 50);

    var singleplayerButton = new TextButton(resourceManager,
        windowSize,
        new ObjectRelativeScenePositioner(logo, new SceneCoordinateOffset(0, 200), 10),
        "Single Player",
        FontName.M5X7,
        Color.white(),
        12,
        buttonSize,
        GENERIC,
        () -> {
          var scene = new HostLobbyScene(eventBus,
              resourceManager,
              windowSize,
              LobbyType.LOCAL);
          eventBus.dispatch(new SceneTransitionEvent(scene));
        });

    var multiplayerButton = new TextButton(resourceManager,
        windowSize,
        new ObjectRelativeScenePositioner(singleplayerButton, new SceneCoordinateOffset(0, 75), 10),
        "Multiplayer",
        FontName.M5X7,
        Color.white(),
        12,
        buttonSize,
        GENERIC,
        () -> {
          var scene = new LobbyListScene(eventBus,
              resourceManager,
              windowSize);
          eventBus.dispatch(new SceneTransitionEvent(scene));
        });

    var helpButton = new TextButton(resourceManager,
        windowSize,
        new ObjectRelativeScenePositioner(multiplayerButton, new SceneCoordinateOffset(0, 75), 10),
        "Help",
        FontName.M5X7,
        Color.white(),
        12,
        buttonSize,
        GENERIC,
        () -> {
          var scene = new HelpScene(resourceManager, windowSize, eventBus);
          eventBus.dispatch(new SceneTransitionEvent(scene));
        });

    var exitButton = new TextButton(resourceManager,
        windowSize,
        new ObjectRelativeScenePositioner(helpButton, new SceneCoordinateOffset(0, 75), 10),
        "Exit",
        FontName.M5X7,
        Color.white(),
        12,
        buttonSize,
        GENERIC,
        () -> eventBus.dispatch(new CloseApplicationEvent()));

    gameObjects.add(singleplayerButton);
    gameObjects.add(multiplayerButton);
    gameObjects.add(helpButton);
    gameObjects.add(exitButton);
    gameObjects.add(logo);
    gameObjects.add(text);

    return gameObjects;
  }

  @Override
  public void initialize() throws Exception {
    super.initialize();
    sceneAudio.initialize();
  }

  @Override
  public void cleanup() {
    super.cleanup();
    sceneAudio.cleanup();
  }

}
