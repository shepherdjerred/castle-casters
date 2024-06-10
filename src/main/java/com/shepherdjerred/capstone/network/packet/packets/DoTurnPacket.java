package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.logic.turn.Turn;

public record DoTurnPacket(Turn turn) implements Packet {

}
