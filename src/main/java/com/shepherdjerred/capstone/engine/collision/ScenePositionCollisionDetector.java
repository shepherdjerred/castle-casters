package com.shepherdjerred.capstone.engine.collision;

import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;
import com.shepherdjerred.capstone.engine.scene.position.ScenePositioner;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * Determines if there is a collision based on a ScenePositioner. Useful for game objects.
 */
@Setter
@AllArgsConstructor
public class ScenePositionCollisionDetector implements CollisionDetector {

  private ScenePositioner scenePositioner;
  private WindowSize windowSize;
  private SceneObjectDimensions dimensions;

  @Override
  public boolean hasCollision(SceneCoordinate coordinate) {
    var position = scenePositioner.getSceneCoordinate(windowSize, dimensions);
    var maxX = position.getX() + dimensions.getWidth();
    var minX = position.getX();
    var maxY = position.getY() + dimensions.getHeight();
    var minY = position.getY();

    return coordinate.getX() <= maxX
        && coordinate.getX() >= minX
        && coordinate.getY() <= maxY
        && coordinate.getY() >= minY;
  }
}
