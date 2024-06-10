package com.shepherdjerred.capstone.game.network.discovery.netty;


import com.shepherdjerred.capstone.common.Constants;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.game.network.discovery.ServerDiscoverer;
import com.shepherdjerred.capstone.game.network.event.NetworkEvent;
import lombok.extern.log4j.Log4j2;

import java.net.InetSocketAddress;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Log4j2
public class NettyServerDiscoverer implements ServerDiscoverer, Runnable {

  private final ConcurrentLinkedQueue<NetworkEvent> eventQueue;
  private NettyDiscoveryBootstrap bootstrap;

  public NettyServerDiscoverer() {
    this.eventQueue = new ConcurrentLinkedQueue<>();
  }

  @Override
  public void discoverServers() {
    log.info("Discovering servers");
    bootstrap = new NettyDiscoveryBootstrap(new InetSocketAddress(Constants.DISCOVERY_PORT),
        eventQueue);
    bootstrap.run();
  }

  @Override
  public void stop() {
    bootstrap.cleanup();
  }

  @Override
  public Optional<Event> getEvent() {
    if (eventQueue.size() > 0) {
      return Optional.of(eventQueue.poll());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void run() {
    discoverServers();
  }
}
