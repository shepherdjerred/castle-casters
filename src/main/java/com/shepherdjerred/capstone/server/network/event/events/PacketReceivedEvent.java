package com.shepherdjerred.capstone.server.network.event.events;

import com.shepherdjerred.capstone.network.packet.packets.Packet;
import com.shepherdjerred.capstone.server.network.server.Connection;
import lombok.Getter;

@Getter
public record PacketReceivedEvent(Connection connection, Packet packet) implements NetworkEvent {

}

