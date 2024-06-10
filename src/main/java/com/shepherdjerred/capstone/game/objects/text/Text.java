package com.shepherdjerred.capstone.game.objects.text;

import com.shepherdjerred.capstone.engine.graphics.Color;
import com.shepherdjerred.capstone.engine.graphics.font.FontName;
import com.shepherdjerred.capstone.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.scene.position.ScenePositioner;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Text implements GameObject {

  private final TextRenderer renderer;
  private final String text;
  private final FontName fontName;
  private final Color color;
  private final int size;
  private final int maxWidth;
  @Getter
  private boolean isInitialized;
  @Setter
  private ScenePositioner position;

  public Text(ResourceManager resourceManager,
              String text,
              FontName fontName,
              Color color,
              int size,
              int maxWidth,
              ScenePositioner position) {
    this.text = text;
    this.fontName = fontName;
    this.color = color;
    this.size = size;
    this.maxWidth = maxWidth;
    this.position = position;
    renderer = new TextRenderer(resourceManager);
  }

  public SceneObjectDimensions getSceneObjectDimensions() {
    return new SceneObjectDimensions(renderer.getWidth(), renderer.getHeight());
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

  }
}
