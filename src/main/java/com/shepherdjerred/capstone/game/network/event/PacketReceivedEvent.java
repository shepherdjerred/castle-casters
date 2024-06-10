package com.shepherdjerred.capstone.game.network.event;

import com.shepherdjerred.capstone.network.packet.packets.Packet;

public record PacketReceivedEvent(Packet packet) implements NetworkEvent {

}
