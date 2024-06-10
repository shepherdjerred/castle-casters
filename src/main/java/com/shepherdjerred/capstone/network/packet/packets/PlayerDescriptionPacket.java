package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import lombok.Getter;

@Getter
public record PlayerDescriptionPacket(PlayerInformation playerInformation) implements Packet {

}
