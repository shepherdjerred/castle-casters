package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PlayerDescriptionPacket implements Packet {

  private final PlayerInformation playerInformation;
}
