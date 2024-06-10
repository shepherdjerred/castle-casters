package com.shepherdjerred.capstone.game.network.event;

import com.shepherdjerred.capstone.network.packet.packets.Packet;
import lombok.Getter;

@Getter
public record PacketReceivedEvent(Packet packet) implements NetworkEvent {

}
