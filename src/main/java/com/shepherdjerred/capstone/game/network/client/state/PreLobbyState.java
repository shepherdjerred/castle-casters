package com.shepherdjerred.capstone.game.network.client.state;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import com.shepherdjerred.capstone.game.event.events.FillSlotsWithAiEvent;
import com.shepherdjerred.capstone.game.event.events.IdentifyPlayerEvent;
import com.shepherdjerred.capstone.game.event.events.PlayerJoinEvent;
import com.shepherdjerred.capstone.game.network.client.NetworkClient;
import com.shepherdjerred.capstone.game.network.event.ServerConnectedEvent;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.network.packet.packets.FillSlotsWithAiPacket;
import com.shepherdjerred.capstone.network.packet.packets.PlayerDescriptionPacket;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;

/**
 * Before the client connects to a server.
 */
@Log4j2
public class PreLobbyState extends AbstractNetworkClientState {

  public PreLobbyState(EventBus<Event> eventBus,
      NetworkClient networkClient) {
    super(eventBus, networkClient);
  }

  protected EventHandlerFrame<Event> createEventHandlerFrame() {
    EventHandlerFrame<Event> frame = new EventHandlerFrame<>();

    frame.registerHandler(IdentifyPlayerEvent.class,
        (event) -> networkClient.sendPacket(new PlayerDescriptionPacket(event.getPlayerInformation())));

    frame.registerHandler(ServerConnectedEvent.class, (event) -> {
      eventBus.dispatch(new IdentifyPlayerEvent(new PlayerInformation(UUID.randomUUID(),
          UUID.randomUUID().toString())));
    });

    frame.registerHandler(PlayerJoinEvent.class, (event) -> {
      networkClient.transition(new LobbyClientState(eventBus, networkClient));
    });

    frame.registerHandler(FillSlotsWithAiEvent.class, (event) -> {
      networkClient.sendPacket(new FillSlotsWithAiPacket());
    });

    return frame;
  }
}
