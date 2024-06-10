package com.shepherdjerred.capstone.engine.scene;

import com.shepherdjerred.capstone.engine.object.AbstractGameObject;
import com.shepherdjerred.capstone.engine.object.GameObjectRenderer;
import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.scene.position.BackgroundScenePositioner;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.Setter;

@Setter
public class AbstractSceneBackground extends AbstractGameObject {

  private boolean isCleanupDisabled;

  public AbstractSceneBackground(GameObjectRenderer<?> renderer, WindowSize windowSize) {
    super(renderer,
        new SceneObjectDimensions(windowSize.width(), windowSize.height()),
        new BackgroundScenePositioner());
  }

  @Override
  public void cleanup() {
    if (!isCleanupDisabled) {
      super.cleanup();
    }
  }
}
