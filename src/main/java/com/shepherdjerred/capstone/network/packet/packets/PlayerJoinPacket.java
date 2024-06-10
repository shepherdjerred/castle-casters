package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.common.player.Player;

public record PlayerJoinPacket(Player player) implements Packet {

}
