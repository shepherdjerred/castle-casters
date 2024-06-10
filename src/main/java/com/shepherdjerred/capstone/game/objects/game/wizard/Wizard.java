package com.shepherdjerred.capstone.game.objects.game.wizard;

import com.shepherdjerred.capstone.common.player.Element;
import com.shepherdjerred.capstone.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.object.GameObjectRenderer;
import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.scene.position.ScenePositioner;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Wizard implements GameObject {

  @Getter
  private boolean isInitialized;
  private final GameObjectRenderer<Wizard> renderer;
  @Getter
  private final Element element;
  @Getter
  @Setter
  private ScenePositioner position;
  @Setter
  @Getter
  private State state;
  @Getter
  private int frame;
  private float frameAccumulator;
  @Getter
  private final SceneObjectDimensions sceneObjectDimensions;

  public Wizard(ResourceManager resourceManager,
      ScenePositioner position,
      Element element,
      SceneObjectDimensions sceneObjectDimensions) {
    this.position = position;
    this.renderer = new WizardRenderer(resourceManager);
    this.element = element;
    this.state = State.STILL;
    this.frame = 0;
    this.sceneObjectDimensions = sceneObjectDimensions;
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
  public void render(WindowSize windowSize) {
    if (!isInitialized) {
      throw new IllegalStateException("Object not initialized");
    }
    renderer.render(windowSize, this);
  }

  @Override
  public void update(float interval) {
    frameAccumulator += interval * 3;
    int maxFrames;
    if (state == State.STILL) {
      maxFrames = 3;
    } else if (state == State.CASTING) {
      maxFrames = 7;
    } else {
      maxFrames = 3;
    }
    frame = (int) frameAccumulator;
    if (frame > maxFrames - 1) {
      frame = 0;
      frameAccumulator = 0;
    }
  }

  public SpriteState getSpriteState() {
    return new SpriteState(state, frame);
  }

  public enum State {
    STILL, CASTING, WALKING_UP, WALKING_DOWN, WALKING_LEFT, WALKING_RIGHT
  }

  @Getter
  @ToString
  @AllArgsConstructor
  @EqualsAndHashCode
  public static class SpriteState {

    private final State state;
    private final int frame;
  }
}
