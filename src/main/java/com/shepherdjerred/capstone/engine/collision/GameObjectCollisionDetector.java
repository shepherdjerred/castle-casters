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
    var maxX = position.getX() + object.getSceneObjectDimensions().getWidth();
    var minX = position.getX();
    var maxY = position.getY() + object.getSceneObjectDimensions().getHeight();
    var minY = position.getY();

    return coordinate.getX() <= maxX
        && coordinate.getX() >= minX
        && coordinate.getY() <= maxY
        && coordinate.getY() >= minY;
  }
}
