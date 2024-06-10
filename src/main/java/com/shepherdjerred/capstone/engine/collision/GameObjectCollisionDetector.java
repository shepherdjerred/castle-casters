package com.shepherdjerred.capstone.engine.collision;

import com.shepherdjerred.capstone.engine.object.GameObject;
import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;
import com.shepherdjerred.capstone.engine.window.WindowSize;

public class GameObjectCollisionDetector implements CollisionDetector {

  private final GameObject object;
  private final WindowSize windowSize;

  public GameObjectCollisionDetector(GameObject object, WindowSize windowSize) {
    this.object = object;
    this.windowSize = windowSize;
  }

  @Override
  public boolean hasCollision(SceneCoordinate coordinate) {
    var position = object.getPosition()
        .getSceneCoordinate(windowSize, object.getSceneObjectDimensions());
    var maxX = position.x() + object.getSceneObjectDimensions().width();
    var minX = position.x();
    var maxY = position.y() + object.getSceneObjectDimensions().height();
    var minY = position.y();

    return coordinate.x() <= maxX
        && coordinate.x() >= minX
        && coordinate.y() <= maxY
        && coordinate.y() >= minY;
  }
}
