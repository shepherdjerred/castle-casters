package com.shepherdjerred.capstone.server.network.manager;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.server.network.broadcast.ServerBroadcast;
import com.shepherdjerred.capstone.server.network.broadcast.netty.NettyBroadcast;
import com.shepherdjerred.capstone.server.network.event.events.PacketReceivedEvent;
import com.shepherdjerred.capstone.server.network.event.handlers.PacketReceivedEventHandler;
import com.shepherdjerred.capstone.server.network.manager.events.StartBroadcastEvent;
import com.shepherdjerred.capstone.server.network.manager.events.StartNetworkEvent;
import com.shepherdjerred.capstone.server.network.manager.events.StopBroadcastEvent;
import com.shepherdjerred.capstone.server.network.manager.events.StopNetworkEvent;
import com.shepherdjerred.capstone.server.network.server.NetworkServer;
import java.net.SocketAddress;

public class NetworkManager {

  private final SocketAddress broadcastAddress;
  private final SocketAddress gameAddress;
  private final EventBus<Event> eventBus;
  private NetworkServer networkServer;
  private ServerBroadcast serverBroadcast;
  private Thread networkThread;
  private Thread broadcastThread;

  public NetworkManager(SocketAddress gameAddress,
      SocketAddress broadcastAddress,
      EventBus<Event> eventBus) {
    this.broadcastAddress = broadcastAddress;
    this.gameAddress = gameAddress;
    this.eventBus = eventBus;
    createEventHandlers();
  }

  private void createEventHandlers() {
    eventBus.registerHandler(PacketReceivedEvent.class, new PacketReceivedEventHandler(eventBus));

    eventBus.registerHandler(StartBroadcastEvent.class, (event) -> {
      startBroadcast(event.getLobby());
    });
    eventBus.registerHandler(StopBroadcastEvent.class, (event) -> {
      stopBroadcast();
    });
    eventBus.registerHandler(StartNetworkEvent.class, (event) -> {
      startNetwork();
    });
    eventBus.registerHandler(StopNetworkEvent.class, (event) -> {
      stopNetwork();
    });
  }

  private void startBroadcast(Lobby lobby) {
    serverBroadcast = new NettyBroadcast(broadcastAddress, eventBus, lobby);
    broadcastThread = new Thread(serverBroadcast, "BROADCAST");
    broadcastThread.start();
  }

  private void stopBroadcast() {
    if (serverBroadcast != null) {
      serverBroadcast.stop();
      serverBroadcast = null;
    }
  }

  private void startNetwork() {
    networkServer = new NetworkServer(eventBus, gameAddress);
    networkThread = new Thread(networkServer, "SERVER_NETWORK");
    networkThread.start();
  }

  private void stopNetwork() {
    if (networkServer != null) {
      networkServer.shutdown();
      networkServer = null;
    }
  }

  public void update() {
    networkServer.update();
  }
}
