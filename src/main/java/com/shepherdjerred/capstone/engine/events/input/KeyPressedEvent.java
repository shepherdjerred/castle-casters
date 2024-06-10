package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.keyboard.Key;
import lombok.Getter;

@Getter
public record KeyPressedEvent(Key key) implements InputEvent {

}
