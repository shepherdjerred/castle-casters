package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.keyboard.Key;

public record KeyPressedEvent(Key key) implements InputEvent {

}
