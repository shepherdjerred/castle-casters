package com.shepherdjerred.capstone.game.network.discovery;

import com.shepherdjerred.capstone.events.Event;

import java.util.Optional;

public interface ServerDiscoverer extends Runnable {

  void discoverServers();

  void stop();

  Optional<Event> getEvent();
}
