package com.shepherdjerred.capstone.engine.events.handlers;

import com.shepherdjerred.capstone.engine.events.ToggleDepthEvent;
import com.shepherdjerred.capstone.engine.graphics.OpenGlHelper;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ToggleDepthEventHandler implements EventHandler<ToggleDepthEvent> {

  private boolean isDepthEnabled = true;

  @Override
  public void handle(ToggleDepthEvent toggleDepthEvent) {
    isDepthEnabled = !isDepthEnabled;
    if (isDepthEnabled) {
      OpenGlHelper.enableDepthBuffer();
    } else {
      OpenGlHelper.disableDepthBuffer();
    }
    log.info("Depth: " + isDepthEnabled);
  }
}
