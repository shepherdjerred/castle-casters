package com.shepherdjerred.capstone.events.handlers;

import com.shepherdjerred.capstone.events.Event;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EventLoggerHandler<T extends Event> implements EventHandler<T> {

  private final Set<Class<Event>> eventFilters = new HashSet<>();

  public void addFilter(Class<Event> eventClass) {
    eventFilters.add(eventClass);
  }

  public void removeFilter(Class<Event> eventClass) {
    eventFilters.remove(eventClass);
  }

  @Override
  public void handle(T event) {
    if (!eventFilters.contains(event.getClass())) {
      log.info(event);
    }
  }
}
