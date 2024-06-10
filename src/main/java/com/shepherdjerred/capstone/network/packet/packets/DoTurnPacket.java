package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.Getter;

@Getter
public record DoTurnPacket(Turn turn) implements Packet {

}
