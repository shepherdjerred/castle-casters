package com.shepherdjerred.capstone.game.objects.game.wall;

import com.shepherdjerred.capstone.engine.object.AbstractGameObject;
import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.scene.position.ScenePositioner;

public class Wall extends AbstractGameObject {

  public Wall(ResourceManager resourceManager,
      SceneObjectDimensions sceneObjectDimensions,
      ScenePositioner position) {
    super(new WallRenderer(resourceManager), sceneObjectDimensions, position);
  }
}
