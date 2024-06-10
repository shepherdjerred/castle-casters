package com.shepherdjerred.capstone.events.handlers;

import com.shepherdjerred.capstone.events.Event;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Set;

@Log4j2
@AllArgsConstructor
public class EventLoggerHandler<T extends Event> implements EventHandler<T> {

  private final Set<Class<?>> eventFilters;

  @Override
  public void handle(T event) {
    if (!eventFilters.contains(event.getClass())) {
      log.info(event);
    }
  }
}
