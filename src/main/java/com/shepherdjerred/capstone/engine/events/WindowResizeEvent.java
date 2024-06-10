package com.shepherdjerred.capstone.engine.events;

import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class WindowResizeEvent implements Event {

  private final WindowSize newWindowSize;
}
