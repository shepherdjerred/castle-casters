package com.shepherdjerred.capstone.engine.collision;

import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;

public interface CollisionDetector {

  boolean hasCollision(SceneCoordinate sceneCoordinate);
}
