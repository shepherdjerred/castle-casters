package com.shepherdjerred.capstone.game.scenes.teamintro;

import com.shepherdjerred.capstone.engine.graphics.Color;
import com.shepherdjerred.capstone.engine.graphics.OpenGlHelper;
import com.shepherdjerred.capstone.engine.graphics.matrices.ProjectionMatrix;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderProgram;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderProgramName;
import com.shepherdjerred.capstone.engine.graphics.shader.ShaderUniform;
import com.shepherdjerred.capstone.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.scene.SceneRenderer;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TeamIntroRenderer implements SceneRenderer<TeamIntroScene> {

  private final ResourceManager resourceManager;
  private WindowSize windowSize;
  private ProjectionMatrix projectionMatrix;
  private ShaderProgram defaultShaderProgram;

  public TeamIntroRenderer(ResourceManager resourceManager,
      WindowSize windowSize) {
    this.resourceManager = resourceManager;
    this.windowSize = windowSize;
  }

  @Override
  public void render(TeamIntroScene scene) {
    OpenGlHelper.clearScreen();
    updateProjectionMatrix();

    defaultShaderProgram.bind();
    defaultShaderProgram.setUniform(ShaderUniform.PROJECTION_MATRIX, projectionMatrix.getMatrix());

    scene.getGameObjects().forEach(element -> element.render(windowSize));
  }

  @Override
  public void initialize(TeamIntroScene scene) throws Exception {
    updateProjectionMatrix();
    createShaderProgram();
    OpenGlHelper.setClearColor(Color.white());

    for (GameObject gameObject : scene.getGameObjects()) {
      gameObject.initialize();
    }
  }

  private void createShaderProgram() throws Exception {
    defaultShaderProgram = resourceManager.get(ShaderProgramName.DEFAULT);
  }

  private void updateProjectionMatrix() {
    projectionMatrix = new ProjectionMatrix(windowSize);
  }

  @Override
  public void cleanup() {
    resourceManager.free(ShaderProgramName.DEFAULT);
  }
}
