package com.shepherdjerred.capstone.server.network.event.handlers;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandler;
import com.shepherdjerred.capstone.network.packet.packets.DoTurnPacket;
import com.shepherdjerred.capstone.network.packet.packets.FillSlotsWithAiPacket;
import com.shepherdjerred.capstone.network.packet.packets.PlayerDescriptionPacket;
import com.shepherdjerred.capstone.network.packet.packets.StartMatchPacket;
import com.shepherdjerred.capstone.server.event.FillSlotsWithAiEvent;
import com.shepherdjerred.capstone.server.event.PlayerInformationReceivedEvent;
import com.shepherdjerred.capstone.server.event.StartGameEvent;
import com.shepherdjerred.capstone.server.event.TryDoTurnEvent;
import com.shepherdjerred.capstone.server.network.event.events.PacketReceivedEvent;
import com.shepherdjerred.capstone.server.network.server.Connection;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class PacketReceivedEventHandler implements EventHandler<PacketReceivedEvent> {

  private final EventBus<Event> eventBus;

  @Override
  public void handle(PacketReceivedEvent event) {
    var packet = event.getPacket();

    if (packet instanceof PlayerDescriptionPacket) {
      handlePlayerDescriptionPacket((PlayerDescriptionPacket) packet, event.getConnection());
    } else if (packet instanceof StartMatchPacket) {
      eventBus.dispatch(new StartGameEvent());
    } else if (packet instanceof FillSlotsWithAiPacket) {
      eventBus.dispatch(new FillSlotsWithAiEvent());
    } else if (packet instanceof DoTurnPacket) {
      eventBus.dispatch(new TryDoTurnEvent(((DoTurnPacket) packet).getTurn()));
    }
  }

  private void handlePlayerDescriptionPacket(PlayerDescriptionPacket packet,
      Connection connection) {
    eventBus.dispatch(new PlayerInformationReceivedEvent(packet.getPlayerInformation(),
        connection));
  }
}
