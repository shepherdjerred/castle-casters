package com.shepherdjerred.capstone.game.event.handlers;

import com.shepherdjerred.capstone.engine.events.input.MouseButtonDownEvent;
import com.shepherdjerred.capstone.engine.scene.Scene;
import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;
import com.shepherdjerred.capstone.engine.scene.attributes.Clickable;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MouseDownClickableHandler implements EventHandler<MouseButtonDownEvent> {

  private final Scene scene;

  @Override
  public void handle(MouseButtonDownEvent mouseButtonDownEvent) {
    scene.getGameObjects().forEach(element -> {
      if (element instanceof Clickable) {
        var orig = mouseButtonDownEvent.getMouseCoordinate();
        var coord = new SceneCoordinate(orig.getX(), orig.getY(), 0);
        if (((Clickable) element).contains(coord)) {
          ((Clickable) element).onClickBegin();
        }
      }
    });
  }
}
