package com.shepherdjerred.capstone.engine.events.handlers.input;

import com.shepherdjerred.capstone.engine.events.input.KeyReleasedEvent;
import com.shepherdjerred.capstone.engine.input.keyboard.KeyTracker;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KeyTrackerKeyReleasedEventHandler implements EventHandler<KeyReleasedEvent> {

  private final KeyTracker keyTracker;

  @Override
  public void handle(KeyReleasedEvent keyReleasedEvent) {
    keyTracker.setKeyAsUnpressed(keyReleasedEvent.getKey());
  }
}
