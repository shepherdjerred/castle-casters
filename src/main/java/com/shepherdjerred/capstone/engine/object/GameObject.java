package com.shepherdjerred.capstone.engine.object;

import com.shepherdjerred.capstone.engine.scene.position.ScenePositioner;
import com.shepherdjerred.capstone.engine.window.WindowSize;

public interface GameObject {

  void initialize() throws Exception;

  void cleanup();

  SceneObjectDimensions getSceneObjectDimensions();

  ScenePositioner getPosition();

  void setPosition(ScenePositioner scenePositioner);

  void render(WindowSize windowSize);

  void update(float interval);

  boolean isInitialized();
}
