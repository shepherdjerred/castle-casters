package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ServerBroadcastPacket implements Packet {

  private final Lobby lobby;
}
