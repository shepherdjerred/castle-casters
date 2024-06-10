package com.shepherdjerred.capstone.server.network.broadcast.netty;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.server.network.broadcast.ServerBroadcast;
import java.net.SocketAddress;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NettyBroadcast implements ServerBroadcast {

  private final NettyBroadcastBootstrap bootstrap;

  public NettyBroadcast(SocketAddress address,
      EventBus<Event> eventBus, Lobby lobby) {
    bootstrap = new NettyBroadcastBootstrap(address, lobby, eventBus);
  }

  @Override
  public void run() {
    bootstrap.run();
  }

  @Override
  public void stop() {
    bootstrap.shutdown();
  }
}
