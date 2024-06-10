package com.shepherdjerred.capstone.game.objects.logo;

import com.shepherdjerred.capstone.engine.graphics.RendererCoordinate;
import com.shepherdjerred.capstone.engine.graphics.matrices.ModelMatrix;
import com.shepherdjerred.capstone.engine.graphics.mesh.Mesh;
import com.shepherdjerred.capstone.engine.graphics.mesh.TexturedMesh;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderProgram;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderProgramName;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderUniform;
import com.shepherdjerred.capstone.engine.graphics.texture.Texture;
import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import com.shepherdjerred.capstone.engine.object.GameObjectRenderer;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.game.objects.logo.Logo.Type;

public class LogoRenderer implements
    GameObjectRenderer<Logo> {

  private final ResourceManager resourceManager;
  private TexturedMesh texturedMesh;
  private ShaderProgram defaultShaderProgram;

  public LogoRenderer(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
  }

  @Override
  public void initialize(Logo gameObject) throws Exception {
    var width = gameObject.getSceneObjectDimensions().width();
    var height = gameObject.getSceneObjectDimensions().height();

    defaultShaderProgram = resourceManager.get(ShaderProgramName.DEFAULT);

    Texture texture;
    if (gameObject.getType() == Type.GAME) {
      texture = resourceManager.get(TextureName.GAME_LOGO);
    } else if (gameObject.getType() == Type.TEAM) {
      texture = resourceManager.get(TextureName.TEAM_LOGO);
    } else {
      throw new UnsupportedOperationException(gameObject.getType().toString());
    }

    var vertices = new float[]{
        0, 0, 0,
        0, height, 0,
        width, 0, 0,
        width, height, 0
    };

    var textureCoordinates = new float[]{
        0, 0,
        0, 1,
        1, 0,
        1, 1
    };

    var indices = new int[]{
        0, 1, 2,
        3, 1, 2
    };

    var mesh = new Mesh(vertices, textureCoordinates, indices);
    texturedMesh = new TexturedMesh(mesh, texture);
  }

  @Override
  public void render(WindowSize windowSize, Logo logo) {
    var pos = logo.getPosition()
        .getSceneCoordinate(windowSize, logo.getSceneObjectDimensions());
    var model = new ModelMatrix(new RendererCoordinate(pos.x(), pos.y(), pos.z()),
        0,
        1).getMatrix();

    defaultShaderProgram.bind();
    defaultShaderProgram.setUniform(ShaderUniform.MODEL_MATRIX, model);

    texturedMesh.render();

    defaultShaderProgram.unbind();
  }

  @Override
  public void cleanup() {
    resourceManager.free(texturedMesh.texture().textureName());
    resourceManager.free(defaultShaderProgram.getShaderProgramName());
    texturedMesh.mesh().cleanup();
  }
}
