package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.keyboard.Key;
import lombok.Getter;

@Getter
public record KeyReleasedEvent(Key key) implements InputEvent {

}
