package com.shepherdjerred.capstone.engine.events.handlers.input;

import com.shepherdjerred.capstone.engine.events.ToggleBlendingEvent;
import com.shepherdjerred.capstone.engine.events.ToggleDepthEvent;
import com.shepherdjerred.capstone.engine.events.ToggleWireframeEvent;
import com.shepherdjerred.capstone.engine.events.input.KeyReleasedEvent;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import lombok.AllArgsConstructor;

import static com.shepherdjerred.capstone.engine.input.keyboard.Key.*;

@AllArgsConstructor
public class KeyReleasedEventHandler implements EventHandler<KeyReleasedEvent> {

  private final EventBus<Event> eventBus;

  @Override
  public void handle(KeyReleasedEvent keyReleasedEvent) {
    var key = keyReleasedEvent.key();
    if (key == ZERO) {
      eventBus.dispatch(new ToggleWireframeEvent());
    }
    if (key == NINE) {
      eventBus.dispatch(new ToggleBlendingEvent());
    }
    if (key == EIGHT) {
      eventBus.dispatch(new ToggleDepthEvent());
    }
  }
}
