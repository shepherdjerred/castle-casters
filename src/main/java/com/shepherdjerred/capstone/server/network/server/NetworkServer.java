package com.shepherdjerred.capstone.server.network.server;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.network.packet.packets.DoTurnPacket;
import com.shepherdjerred.capstone.network.packet.packets.Packet;
import com.shepherdjerred.capstone.network.packet.packets.PlayerJoinPacket;
import com.shepherdjerred.capstone.network.packet.packets.StartMatchPacket;
import com.shepherdjerred.capstone.server.event.DoTurnEvent;
import com.shepherdjerred.capstone.server.event.PlayerJoinEvent;
import com.shepherdjerred.capstone.server.event.StartGameEvent;
import com.shepherdjerred.capstone.server.network.server.netty.NettyServerBootstrap;
import java.net.SocketAddress;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NetworkServer implements Runnable {

  private final EventBus<Event> eventBus;
  private final NettyServerBootstrap bootstrap;
  private final BiMap<Player, Connection> playerMap;

  public NetworkServer(EventBus<Event> eventBus, SocketAddress address) {
    this.eventBus = eventBus;
    bootstrap = new NettyServerBootstrap(address);
    playerMap = HashBiMap.create();
    registerHandlers();
  }

  private void registerHandlers() {
    var frame = new EventHandlerFrame<>();

    frame.registerHandler(PlayerJoinEvent.class, (event) -> {
      if (event.getConnection() != null) {
        setPlayerConnection(event.getPlayer(), event.getConnection());
      }
      send(new PlayerJoinPacket(event.getPlayer()));
    });

    frame.registerHandler(StartGameEvent.class, (event) -> {
      send(new StartMatchPacket());
    });

    frame.registerHandler(DoTurnEvent.class, (event) -> {
      send(new DoTurnPacket(event.getTurn()));
    });

    eventBus.registerHandlerFrame(frame);
  }

  private void setPlayerConnection(Player player, Connection connection) {
    playerMap.put(player, connection);
  }

  private Connection getConnection(Player player) {
    return playerMap.get(player);
  }

  private void send(Packet packet) {
    playerMap.values().forEach(connection -> connection.sendPacket(packet));
  }

  public void update() {
    var event = bootstrap.getNextEvent();
    event.ifPresent(eventBus::dispatch);
  }

  public void shutdown() {
    bootstrap.shutdown();
  }

  @Override
  public void run() {
    bootstrap.run();
  }
}
