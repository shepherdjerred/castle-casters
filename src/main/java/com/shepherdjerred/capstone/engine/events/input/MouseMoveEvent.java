package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.mouse.MouseCoordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MouseMoveEvent implements InputEvent {

  private final MouseCoordinate newMousePosition;
}
