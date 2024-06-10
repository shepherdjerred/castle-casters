package com.shepherdjerred.capstone.engine.events.handlers;

import static org.lwjgl.opengl.GL11.glViewport;

import com.shepherdjerred.capstone.engine.events.WindowResizeEvent;
import com.shepherdjerred.capstone.events.handlers.EventHandler;

public class OpenGlWindowResizeHandler implements EventHandler<WindowResizeEvent> {

  @Override
  public void handle(WindowResizeEvent windowResizeEvent) {
    var width = windowResizeEvent.getNewWindowSize().getWidth();
    var height = windowResizeEvent.getNewWindowSize().getHeight();
    glViewport(0, 0, width, height);
  }
}
