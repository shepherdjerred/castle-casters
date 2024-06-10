package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.mouse.MouseButton;
import com.shepherdjerred.capstone.engine.input.mouse.MouseCoordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MouseButtonUpEvent implements InputEvent {

  private final MouseButton button;
  private final MouseCoordinate mouseCoordinate;
}
