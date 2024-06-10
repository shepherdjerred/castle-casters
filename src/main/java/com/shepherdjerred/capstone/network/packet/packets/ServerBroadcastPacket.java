package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import lombok.Getter;

@Getter
public record ServerBroadcastPacket(Lobby lobby) implements Packet {

}
