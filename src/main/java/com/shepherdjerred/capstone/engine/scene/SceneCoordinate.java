package com.shepherdjerred.capstone.engine.scene;

import com.shepherdjerred.capstone.engine.scene.position.SceneCoordinateOffset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SceneCoordinate {

  private final float x;
  private final float y;
  private final float z;

  public SceneCoordinate withOffset(SceneCoordinateOffset offset) {
    return new SceneCoordinate(x + offset.getXOffset(), y + offset.getYOffset(), z);
  }
}
