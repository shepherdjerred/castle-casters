package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.common.player.PlayerInformation;

public record PlayerDescriptionPacket(PlayerInformation playerInformation) implements Packet {

}
