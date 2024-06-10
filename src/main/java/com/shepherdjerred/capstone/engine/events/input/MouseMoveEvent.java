package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.mouse.MouseCoordinate;
import lombok.Getter;

@Getter
public record MouseMoveEvent(MouseCoordinate newMousePosition) implements InputEvent {

}
