package com.shepherdjerred.capstone.engine.window;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class WindowSettings {

  private final String title;
  private final WindowSize windowSize;
  private final boolean isVsyncEnabled;
  private final boolean isDebugEnabled;
}
