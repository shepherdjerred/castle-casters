package com.shepherdjerred.capstone.events;

import com.shepherdjerred.capstone.events.handlers.EventHandler;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Thread-safe event queue.
 */
public class ThreadSafeEventBus<T extends Event> {

  private final Queue<T> queue;
  private final Set<EventHandler<T>> genericHandlers;
  private final Map<Class<T>, Set<EventHandler<T>>> handlers;

  public ThreadSafeEventBus() {
    queue = new ConcurrentLinkedQueue<>();
    genericHandlers = new HashSet<>();
    handlers = new ConcurrentHashMap<>();
  }

  @SuppressWarnings("unchecked")
  public <U extends T> void registerHandler(Class<U> eventClass, EventHandler<U> handler) {
    var existingHandlers = getHandlers((Class<T>) eventClass);
    existingHandlers.add((EventHandler<T>) handler);
    handlers.put((Class<T>) eventClass, existingHandlers);
  }

  public void registerHandler(EventHandler<T> handler) {
    genericHandlers.add(handler);
  }

  @SuppressWarnings("unchecked")
  public <U extends T> void removeHandler(Class<U> eventClass, EventHandler<U> handler) {
    handlers.getOrDefault(eventClass, new HashSet<>()).remove(handler);
  }

  @SuppressWarnings("unchecked")
  public void handleLatestEvent() {
    var event = queue.remove();
    var handlers = getHandlers((Class<T>) event.getClass());
    handlers.forEach(handler -> handler.handle(event));
    genericHandlers.forEach(handler -> handler.handle(event));
  }

  public boolean hasEvent() {
    return !isEmpty();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public void dispatch(T event) {
    queue.add(event);
  }

  private Set<EventHandler<T>> getHandlers(Class<T> eventClass) {
    return handlers.getOrDefault(eventClass, new HashSet<>());
  }
}
