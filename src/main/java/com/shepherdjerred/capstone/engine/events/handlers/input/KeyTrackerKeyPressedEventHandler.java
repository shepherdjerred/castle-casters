package com.shepherdjerred.capstone.engine.events.handlers.input;

import com.shepherdjerred.capstone.engine.events.input.KeyPressedEvent;
import com.shepherdjerred.capstone.engine.input.keyboard.KeyTracker;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KeyTrackerKeyPressedEventHandler implements EventHandler<KeyPressedEvent> {

  private final KeyTracker keyTracker;

  @Override
  public void handle(KeyPressedEvent keyPressedEvent) {
    keyTracker.setKeyAsPressed(keyPressedEvent.getKey());
  }
}
