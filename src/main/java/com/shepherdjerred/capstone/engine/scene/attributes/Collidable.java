package com.shepherdjerred.capstone.engine.scene.attributes;

import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;

/**
 * An object that takes up space in a scenes.
 */
public interface Collidable {

  /**
   * Returns whether a coordinate is contained within an object.
   *
   * @param sceneCoordinate The coordinate to test
   * @return True if the object contains the coordinate, false otherwise
   */
  boolean contains(SceneCoordinate sceneCoordinate);
}
