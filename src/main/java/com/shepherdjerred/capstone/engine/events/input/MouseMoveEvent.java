package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.mouse.MouseCoordinate;

public record MouseMoveEvent(MouseCoordinate newMousePosition) implements InputEvent {

}
