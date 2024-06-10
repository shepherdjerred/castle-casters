package com.shepherdjerred.capstone.engine.scene.position;

import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;
import com.shepherdjerred.capstone.engine.window.WindowSize;

public interface ScenePositioner {

  SceneCoordinate getSceneCoordinate(WindowSize windowSize,
                                     SceneObjectDimensions sceneObjectDimensions);

  SceneCoordinateOffset getOffset();

  void setOffset(SceneCoordinateOffset offset);
}
