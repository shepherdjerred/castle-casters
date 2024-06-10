package com.shepherdjerred.capstone.network.packet.packets;

import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class DoTurnPacket implements Packet {

  private final Turn turn;
}
