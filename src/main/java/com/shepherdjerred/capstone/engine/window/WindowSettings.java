package com.shepherdjerred.capstone.engine.window;

import lombok.Getter;

@Getter
public record WindowSettings(String title, WindowSize windowSize, boolean isVsyncEnabled, boolean isDebugEnabled) {

}
