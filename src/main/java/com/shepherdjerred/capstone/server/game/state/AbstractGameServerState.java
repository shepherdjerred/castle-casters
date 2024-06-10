package com.shepherdjerred.capstone.server.game.state;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.server.game.GameLogic;

public abstract class AbstractGameServerState implements GameServerState {

  protected final GameLogic gameLogic;
  protected final EventBus<Event> eventBus;
  private final EventHandlerFrame<Event> eventHandlerFrame;

  public AbstractGameServerState(GameLogic gameLogic, EventBus<Event> eventBus) {
    this.gameLogic = gameLogic;
    this.eventBus = eventBus;
    this.eventHandlerFrame = createEventHandlerFrame();
  }

  protected abstract EventHandlerFrame<Event> createEventHandlerFrame();

  @Override
  public void enable() {
    eventBus.registerHandlerFrame(eventHandlerFrame);
  }

  @Override
  public void disable() {
    eventBus.removeHandlerFrame(eventHandlerFrame);
  }
}
