package com.shepherdjerred.capstone.events;

import com.shepherdjerred.capstone.events.handlers.EventHandler;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Non-thread-safe event bus.
 */
@SuppressWarnings("unchecked")
public class EventBus<T extends Event> {

  private final Set<EventHandler<T>> genericHandlers;
  private final Map<Class<T>, Set<EventHandler<T>>> handlers;

  public EventBus() {
    this.genericHandlers = new HashSet<>();
    this.handlers = new HashMap<>();
  }

  public <U extends T> void register(Class<U> eventClass, EventHandler<U> handler) {
    registerHandler(eventClass, handler);
  }

  public <U extends T> void registerHandler(Class<U> eventClass, EventHandler<U> handler) {
    var existingHandlers = getHandlers((Class<T>) eventClass);
    existingHandlers.add((EventHandler<T>) handler);
    handlers.put((Class<T>) eventClass, existingHandlers);
  }

  public <U extends T> void remove(Class<U> eventClass, EventHandler<U> handler) {
    removeHandler(eventClass, handler);
  }

  public <U extends T> void removeHandler(Class<U> eventClass, EventHandler<U> handler) {
    handlers.getOrDefault(eventClass, new HashSet<>()).remove(handler);
  }

  public void register(EventHandler<T> handler) {
    registerHandler(handler);
  }

  public void registerHandler(EventHandler<T> handler) {
    genericHandlers.add(handler);
  }

  public void remove(EventHandler<T> handler) {
    removeHandler(handler);
  }

  public void removeHandler(EventHandler<T> handler) {
    genericHandlers.remove(handler);
  }

  public void register(EventHandlerFrame<T> handlerFrame) {
    registerHandlerFrame(handlerFrame);
  }

  public void registerHandlerFrame(EventHandlerFrame<T> handlerFrame) {
    handlerFrame.getHandlers()
        .forEach((type, handlers) -> handlers.forEach(handler -> registerHandler(type, handler)));
  }

  public void remove(EventHandlerFrame<T> handlerFrame) {
    removeHandlerFrame(handlerFrame);
  }

  public void removeHandlerFrame(EventHandlerFrame<T> handlerFrame) {
    handlerFrame.getHandlers()
        .forEach((type, handlers) -> handlers.forEach(handler -> removeHandler(type, handler)));
  }

  public void dispatch(T event) {
    var handlers = new HashSet<>(getHandlers((Class<T>) event.getClass()));
    handlers.forEach(handler -> handler.handle(event));

    var genericHandlersCopy = new HashSet<>(genericHandlers);
    genericHandlersCopy.forEach(handler -> handler.handle(event));
  }

  private Set<EventHandler<T>> getHandlers(Class<T> eventClass) {
    return handlers.getOrDefault(eventClass, new HashSet<>());
  }
}
