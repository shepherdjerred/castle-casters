package com.shepherdjerred.capstone.game.network.event;

import com.shepherdjerred.capstone.network.packet.packets.Packet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PacketReceivedEvent implements NetworkEvent {

  private final Packet packet;
}
