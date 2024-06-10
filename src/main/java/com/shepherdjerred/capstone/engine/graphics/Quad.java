package com.shepherdjerred.capstone.engine.graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * A 3D quad.
 */
@Getter
@ToString
@AllArgsConstructor
public class Quad {

  private final RendererCoordinate topRight;
  private final RendererCoordinate topLeft;
  private final RendererCoordinate bottomLeft;
  private final RendererCoordinate bottomRight;

  /**
   * Converts this object to an array of floats. Coordinates are in the following order: Bottom
   * left, top left, bottom right, top right.
   */
  public float[] toFloatArray() {
    return new float[] {
        bottomLeft.getX(), bottomLeft.getY(), bottomLeft.getZ(),
        topLeft.getX(), topLeft.getY(), topLeft.getZ(),
        bottomRight.getX(), bottomRight.getY(), bottomRight.getZ(),
        topRight.getX(), topRight.getY(), topRight.getZ()
    };
  }
}
