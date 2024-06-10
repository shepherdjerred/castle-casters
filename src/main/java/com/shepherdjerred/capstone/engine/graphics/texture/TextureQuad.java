package com.shepherdjerred.capstone.engine.graphics.texture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * A 2D texture quad.
 */
@Getter
@ToString
@AllArgsConstructor
public class TextureQuad {

  private final TextureCoordinate topRight;
  private final TextureCoordinate topLeft;
  private final TextureCoordinate bottomLeft;
  private final TextureCoordinate bottomRight;

  /**
   * Converts this object to an array of floats. Coordinates are in the following order: Bottom
   * left, top left, bottom right, top right.
   */
  public float[] toFloatArray() {
    return new float[] {
        bottomLeft.getX(), bottomLeft.getY(),
        topLeft.getX(), topLeft.getY(),
        bottomRight.getX(), bottomRight.getY(),
        topRight.getX(), topRight.getY()
    };
  }
}
