package com.shepherdjerred.capstone.game.network.client.state;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.game.event.events.TryDoTurnEvent;
import com.shepherdjerred.capstone.game.network.client.NetworkClient;
import com.shepherdjerred.capstone.network.packet.packets.DoTurnPacket;

public class MatchClientState extends AbstractNetworkClientState {

  public MatchClientState(EventBus<Event> eventBus,
                          NetworkClient networkClient) {
    super(eventBus, networkClient);
  }

  @Override
  protected EventHandlerFrame<Event> createEventHandlerFrame() {
    EventHandlerFrame<Event> frame = new EventHandlerFrame<>();

    frame.registerHandler(TryDoTurnEvent.class, (event) -> networkClient.sendPacket(new DoTurnPacket(event.turn())));

    return frame;
  }
}
