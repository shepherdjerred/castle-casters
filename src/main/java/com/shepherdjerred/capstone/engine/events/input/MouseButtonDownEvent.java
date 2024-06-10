package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.mouse.MouseButton;
import com.shepherdjerred.capstone.engine.input.mouse.MouseCoordinate;
import lombok.Getter;

@Getter
public record MouseButtonDownEvent(MouseButton button, MouseCoordinate mouseCoordinate) implements InputEvent {

}
