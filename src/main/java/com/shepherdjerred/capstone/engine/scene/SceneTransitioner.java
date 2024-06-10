package com.shepherdjerred.capstone.engine.scene;

import com.shepherdjerred.capstone.engine.events.scene.SceneActiveEvent;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class SceneTransitioner {

  private final EventBus<Event> eventBus;
  @Getter
  private Scene scene;
  private boolean isTransitioning;

  public SceneTransitioner(EventBus<Event> eventBus) {
    this.eventBus = eventBus;
  }

  public void initialize(Scene scene) throws Exception {
    this.scene = scene;
    scene.initialize();
  }

  public void transition(Scene newScene) throws Exception {
    if (!isTransitioning) {
      isTransitioning = true;
      var oldScene = scene;
      newScene.initialize();
      scene = newScene;
      oldScene.cleanup();
      isTransitioning = false;
      eventBus.dispatch(new SceneActiveEvent());
    } else {
      log.info("Ignoring transition because one is already in progress.");
    }
  }

  public void cleanup() {
    scene.cleanup();
  }
}
