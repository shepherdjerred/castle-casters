package com.shepherdjerred.capstone.logic.turn;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class JumpPawnStraightTurn implements JumpPawnTurn {

  private final QuoridorPlayer causer;
  private final Coordinate source;
  private final Coordinate destination;
  private final Coordinate pivot;
}
