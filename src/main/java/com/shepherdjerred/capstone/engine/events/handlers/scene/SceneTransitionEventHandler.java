package com.shepherdjerred.capstone.engine.events.handlers.scene;

import com.shepherdjerred.capstone.engine.events.scene.SceneTransitionEvent;
import com.shepherdjerred.capstone.engine.scene.SceneTransitioner;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SceneTransitionEventHandler implements EventHandler<SceneTransitionEvent> {

  private final SceneTransitioner sceneTransitioner;

  @Override
  public void handle(SceneTransitionEvent sceneTransitionEvent) {
    try {
      sceneTransitioner.transition(sceneTransitionEvent.getNewScene());
    } catch (Exception e) {
      throw new RuntimeException("Error transitioning scenes", e);
    }
  }
}
