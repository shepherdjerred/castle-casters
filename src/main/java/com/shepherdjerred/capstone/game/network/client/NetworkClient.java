package com.shepherdjerred.capstone.game.network.client;

import com.shepherdjerred.capstone.game.network.client.handler.PacketReceivedEventHandler;
import com.shepherdjerred.capstone.game.network.client.netty.NettyClientBootstrap;
import com.shepherdjerred.capstone.game.network.client.state.PreLobbyState;
import com.shepherdjerred.capstone.game.network.client.state.NetworkClientState;
import com.shepherdjerred.capstone.game.network.event.PacketReceivedEvent;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.network.packet.packets.Packet;
import java.net.SocketAddress;
import lombok.extern.log4j.Log4j2;

/**
 * Hooks into an EventBus and sends/receives events over a network.
 */
@Log4j2
public class NetworkClient {

  private NetworkClientState clientState;
  private final EventBus<Event> eventBus;
  private NettyClientBootstrap bootstrap;

  public NetworkClient(EventBus<Event> eventBus) {
    this.eventBus = eventBus;
    this.bootstrap = new NettyClientBootstrap();
    clientState = new PreLobbyState(eventBus, this);
    clientState.enable();
    eventBus.register(PacketReceivedEvent.class, new PacketReceivedEventHandler(eventBus));
  }

  public void sendPacket(Packet packet) {
    bootstrap.send(packet);
  }

  public void connect(SocketAddress socketAddress) {
    bootstrap.connect(socketAddress);
  }

  public void shutdown() {
    bootstrap.cleanup();
  }

  public void update() {
    var event = bootstrap.getLatestEvent();
    event.ifPresent(eventBus::dispatch);
  }

  public void transition(NetworkClientState newState) {
    log.info("Transitioning to new client state " + newState);
    clientState.disable();
    newState.enable();
    clientState = newState;
  }
}
