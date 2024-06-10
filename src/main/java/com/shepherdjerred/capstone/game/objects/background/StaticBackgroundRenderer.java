package com.shepherdjerred.capstone.game.objects.background;

import com.shepherdjerred.capstone.engine.graphics.mesh.Mesh;
import com.shepherdjerred.capstone.engine.graphics.mesh.TexturedMesh;
import com.shepherdjerred.capstone.engine.graphics.texture.Texture;
import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import com.shepherdjerred.capstone.engine.object.GameObjectRenderer;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.window.WindowSize;

public class StaticBackgroundRenderer implements
    GameObjectRenderer<StaticBackground> {

  private TexturedMesh texturedMesh;
  private final ResourceManager resourceManager;
  private WindowSize windowSize;

  public StaticBackgroundRenderer(ResourceManager resourceManager, WindowSize windowSize) {
    this.windowSize = windowSize;
    this.resourceManager = resourceManager;
  }

  @Override
  public void initialize(StaticBackground gameObject) throws Exception {
    var width = windowSize.getWidth();
    var height = windowSize.getHeight();

    Texture texture;
    var type = gameObject.getType();

    switch (type) {
      case PURPLE_MOUNTAINS:
        texture = resourceManager.get(TextureName.PURPLE_MOUNTAINS);
        break;
      default:
        throw new UnsupportedOperationException(gameObject.getType().toString());
    }

    var vertices = new float[] {
        0, 0, 0,
        0, height, 0,
        width, 0, 0,
        width, height, 0
    };

    var textureCoordinates = new float[] {
        0, 0,
        0, 1,
        1, 0,
        1, 1
    };

    var indices = new int[] {
        0, 1, 2,
        3, 1, 2
    };

    var mesh = new Mesh(vertices, textureCoordinates, indices);
    texturedMesh = new TexturedMesh(mesh, texture);
  }

  @Override
  public void render(WindowSize windowSize, StaticBackground sceneElement) {
    // TODO bind shader program, etc.
    texturedMesh.render();
  }

  @Override
  public void cleanup() {
    resourceManager.free(texturedMesh.getTexture().getTextureName());
    texturedMesh.getMesh().cleanup();
  }
}
