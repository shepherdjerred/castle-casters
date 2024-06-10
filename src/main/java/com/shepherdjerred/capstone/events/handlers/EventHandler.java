package com.shepherdjerred.capstone.events.handlers;


import com.shepherdjerred.capstone.events.Event;

public interface EventHandler<T extends Event> {
  void handle(T event);
}
