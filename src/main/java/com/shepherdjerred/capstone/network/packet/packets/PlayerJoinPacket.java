package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.common.player.Player;
import lombok.Getter;

@Getter
public record PlayerJoinPacket(Player player) implements Packet {

}
