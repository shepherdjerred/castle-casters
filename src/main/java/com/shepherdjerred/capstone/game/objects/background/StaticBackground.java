package com.shepherdjerred.capstone.game.objects.background;

import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.object.GameObjectRenderer;
import com.shepherdjerred.capstone.engine.scene.position.ScenePositioner;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A background spanning the entire window.
 */
@Getter
@ToString
@AllArgsConstructor
public class StaticBackground implements GameObject {

  private boolean isInitialized;
  private final GameObjectRenderer<StaticBackground> renderer;
  @Setter
  private ScenePositioner position;
  private final Type type;

  public StaticBackground(GameObjectRenderer<StaticBackground> renderer,
      ScenePositioner position,
      Type type) {
    this.renderer = renderer;
    this.position = position;
    this.type = type;
  }

  @Override
  public void initialize() throws Exception {
    renderer.initialize(this);
    isInitialized = true;
  }

  @Override
  public void cleanup() {
    isInitialized = false;
    renderer.cleanup();
  }

  @Override
  public SceneObjectDimensions getSceneObjectDimensions() {
    // TODO?
    return new SceneObjectDimensions(0, 0);
  }

  @Override
  public void render(WindowSize windowSize) {
    if (!isInitialized) {
      throw new IllegalStateException("Object not initialized");
    }
    renderer.render(windowSize, this);
  }

  @Override
  public void update(float interval) {

  }

  public enum Type {
    PURPLE_MOUNTAINS,
    RED_PLAINS
  }
}
