package com.shepherdjerred.capstone.engine.scene.position;

import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.Getter;
import lombok.Setter;

public class MapPositioner implements ScenePositioner {

  @Getter
  @Setter
  private SceneCoordinateOffset offset;

  @Override
  public SceneCoordinate getSceneCoordinate(WindowSize windowSize,
      SceneObjectDimensions sceneObjectDimensions) {
    return null;
  }

}
