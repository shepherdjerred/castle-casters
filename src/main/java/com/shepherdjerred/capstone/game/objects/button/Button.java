package com.shepherdjerred.capstone.game.objects.button;

import com.shepherdjerred.capstone.engine.collision.GameObjectCollisionDetector;
import com.shepherdjerred.capstone.engine.object.ClickableAbstractGameObject;
import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.scene.position.ScenePositioner;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Button extends ClickableAbstractGameObject {

  private final Type type;

  public Button(ResourceManager resourceManager,
                WindowSize windowSize,
                ScenePositioner position,
                SceneObjectDimensions sceneObjectDimensions,
                Type type,
                Runnable onClick) {
    super(new ButtonRenderer(resourceManager), sceneObjectDimensions, position, null, onClick);
    this.type = type;
    setCollisionDetector(new GameObjectCollisionDetector(this, windowSize));
  }

  public enum Type {
    HOME,
    GENERIC
  }
}
