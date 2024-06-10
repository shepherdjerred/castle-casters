package com.shepherdjerred.capstone.engine.events;

import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.events.Event;

public record WindowResizeEvent(WindowSize newWindowSize) implements Event {

}
