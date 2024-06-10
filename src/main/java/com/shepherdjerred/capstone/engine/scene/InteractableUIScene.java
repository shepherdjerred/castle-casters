package com.shepherdjerred.capstone.engine.scene;

import com.shepherdjerred.capstone.engine.events.input.MouseButtonDownEvent;
import com.shepherdjerred.capstone.engine.events.input.MouseButtonUpEvent;
import com.shepherdjerred.capstone.engine.events.input.MouseMoveEvent;
import com.shepherdjerred.capstone.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.game.event.handlers.MouseDownClickableHandler;
import com.shepherdjerred.capstone.game.event.handlers.MouseMoveHoverableEventHandler;
import com.shepherdjerred.capstone.game.event.handlers.MouseUpClickableHandler;

public abstract class InteractableUIScene extends AbstractUIScene {

  protected final EventBus<Event> eventBus;
  private final EventHandlerFrame<Event> interactableHandlerFrame;

  public InteractableUIScene(WindowSize windowSize,
                             ResourceManager resourceManager,
                             SceneRenderer renderer,
                             EventBus<Event> eventBus) {
    super(resourceManager, windowSize, renderer);
    interactableHandlerFrame = new EventHandlerFrame<>();
    this.eventBus = eventBus;
    createEventHandlerFrame();
  }

  private void createEventHandlerFrame() {
    var mouseDownClickable = new MouseDownClickableHandler(this);
    var mouseUpClickable = new MouseUpClickableHandler(this);
    var mouseMoveHoverable = new MouseMoveHoverableEventHandler(this);

    interactableHandlerFrame.registerHandler(MouseButtonDownEvent.class, mouseDownClickable);
    interactableHandlerFrame.registerHandler(MouseButtonUpEvent.class, mouseUpClickable);
    interactableHandlerFrame.registerHandler(MouseMoveEvent.class, mouseMoveHoverable);
  }

  @Override
  public void initialize() throws Exception {
    super.initialize();
    eventBus.registerHandlerFrame(interactableHandlerFrame);
  }

  @Override
  public void cleanup() {
    super.cleanup();
    eventBus.removeHandlerFrame(interactableHandlerFrame);
  }
}
