package com.shepherdjerred.capstone.engine.events.input;

import com.shepherdjerred.capstone.engine.input.keyboard.Key;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class KeyPressedEvent implements InputEvent {

  private final Key key;
}
