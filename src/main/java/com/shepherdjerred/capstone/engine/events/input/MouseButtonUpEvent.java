package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.mouse.MouseButton;
import com.shepherdjerred.capstone.engine.input.mouse.MouseCoordinate;

public record MouseButtonUpEvent(MouseButton button, MouseCoordinate mouseCoordinate) implements InputEvent {

}
