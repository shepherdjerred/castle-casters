package com.shepherdjerred.capstone.engine.scene;

import com.shepherdjerred.capstone.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.game.objects.background.parallax.ParallaxBackground;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractUIScene implements UIScene {

  protected final ResourceManager resourceManager;
  protected final WindowSize windowSize;
  private final SceneRenderer sceneRenderer;
  protected final Set<GameObject> gameObjects;
  protected ParallaxBackground background;

  public AbstractUIScene(ResourceManager resourceManager,
      WindowSize windowSize,
      SceneRenderer sceneRenderer) {
    this.resourceManager = resourceManager;
    this.windowSize = windowSize;
    this.sceneRenderer = sceneRenderer;
    this.gameObjects = new HashSet<>();
    background = null;
  }

  public Set<GameObject> getGameObjects() {
    var objects = new HashSet<>(gameObjects);
    if (background != null) {
      objects.add(background);
    }
    return objects;
  }

  protected abstract Set<GameObject> createGameObjects();

  @Override
  public void initialize() throws Exception {
    gameObjects.addAll(createGameObjects());
    sceneRenderer.initialize(this);
    for (GameObject gameObject : getGameObjects()) {
      gameObject.initialize();
    }
  }

  @Override
  public void render(WindowSize windowSize) {
    sceneRenderer.render(this);
  }

  @Override
  public void cleanup() {
    getGameObjects().forEach(GameObject::cleanup);
    sceneRenderer.cleanup();
  }

  @Override
  public void updateState(float interval) {
    getGameObjects().forEach(gameObject -> gameObject.update(interval));
  }

}
