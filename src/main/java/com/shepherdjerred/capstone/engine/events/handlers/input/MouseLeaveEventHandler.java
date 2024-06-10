package com.shepherdjerred.capstone.engine.events.handlers.input;

import com.shepherdjerred.capstone.engine.events.input.MouseLeaveEvent;
import com.shepherdjerred.capstone.engine.input.mouse.MouseTracker;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MouseLeaveEventHandler implements EventHandler<MouseLeaveEvent> {

  private final MouseTracker mouseTracker;

  @Override
  public void handle(MouseLeaveEvent mouseLeaveEvent) {
    mouseTracker.setInWindow(false);
  }
}
