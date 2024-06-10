package com.shepherdjerred.capstone.game.network.client.state;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.game.event.events.FillSlotsWithAiEvent;
import com.shepherdjerred.capstone.game.event.events.StartGameEvent;
import com.shepherdjerred.capstone.game.event.events.TryStartGameEvent;
import com.shepherdjerred.capstone.game.network.client.NetworkClient;
import com.shepherdjerred.capstone.network.packet.packets.FillSlotsWithAiPacket;
import com.shepherdjerred.capstone.network.packet.packets.StartMatchPacket;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LobbyClientState extends AbstractNetworkClientState {

  public LobbyClientState(EventBus<Event> eventBus,
                          NetworkClient networkClient) {
    super(eventBus, networkClient);
  }

  @Override
  protected EventHandlerFrame<Event> createEventHandlerFrame() {
    var frame = new EventHandlerFrame<>();

    frame.registerHandler(FillSlotsWithAiEvent.class, (event) -> networkClient.sendPacket(new FillSlotsWithAiPacket()));

    frame.registerHandler(TryStartGameEvent.class, (event) -> networkClient.sendPacket(new StartMatchPacket()));

    frame.registerHandler(StartGameEvent.class, (event) -> networkClient.transition(new MatchClientState(eventBus, networkClient)));

    return frame;
  }
}
