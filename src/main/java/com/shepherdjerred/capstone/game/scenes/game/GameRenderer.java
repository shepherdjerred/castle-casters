package com.shepherdjerred.capstone.game.scenes.game;

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
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;

public class GameRenderer implements SceneRenderer<GameScene> {

  private final ResourceManager resourceManager;
  private final EventBus<Event> eventBus;
  private final WindowSize windowSize;
  private final EventHandlerFrame<Event> eventHandlerFrame;
  private ProjectionMatrix projectionMatrix;
  private ShaderProgram textShaderProgram;
  private ShaderProgram defaultShaderProgram;

  public GameRenderer(ResourceManager resourceManager,
                      EventBus<Event> eventBus,
                      WindowSize windowSize) {
    this.resourceManager = resourceManager;
    this.eventBus = eventBus;
    this.eventHandlerFrame = new EventHandlerFrame<>();
    this.windowSize = windowSize;
  }

  @Override
  public void initialize(GameScene scene) throws Exception {
    updateProjectionMatrix();
    createShaderProgram();
    OpenGlHelper.setClearColor(Color.black());
    OpenGlHelper.enableDepthBuffer();
    OpenGlHelper.enableTransparency();

    for (GameObject gameObject : scene.getGameObjects()) {
      gameObject.initialize();
    }

    eventBus.registerHandlerFrame(eventHandlerFrame);
  }

  private void createShaderProgram() throws Exception {
    defaultShaderProgram = resourceManager.get(ShaderProgramName.DEFAULT);
    textShaderProgram = resourceManager.get(ShaderProgramName.TEXT);
  }

  @Override
  public void render(GameScene scene) {
    OpenGlHelper.clearScreen();
    updateProjectionMatrix();

    defaultShaderProgram.bind();
    defaultShaderProgram.setUniform(ShaderUniform.PROJECTION_MATRIX, projectionMatrix.getMatrix());
//    textShaderProgram.bind();
//    textShaderProgram.setUniform(ShaderUniform.PROJECTION_MATRIX, projectionMatrix.getMatrix());

    scene.getGameObjects().forEach(element -> element.render(windowSize));
  }

  private void updateProjectionMatrix() {
    projectionMatrix = new ProjectionMatrix(windowSize);
  }

  @Override
  public void cleanup() {
    resourceManager.free(ShaderProgramName.TEXT);
    resourceManager.free(ShaderProgramName.DEFAULT);
    eventBus.removeHandlerFrame(eventHandlerFrame);
  }
}
