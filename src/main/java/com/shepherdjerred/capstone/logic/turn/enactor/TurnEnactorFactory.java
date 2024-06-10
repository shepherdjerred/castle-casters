package com.shepherdjerred.capstone.logic.turn.enactor;

import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TurnEnactorFactory {

  public TurnEnactor getEnactor(Turn turn) {
    if (turn instanceof MovePawnTurn) {
      return new MovePawnTurnEnactor();
    } else if (turn instanceof PlaceWallTurn) {
      return new PlaceWallTurnEnactor();
    } else {
      throw new UnsupportedOperationException("Unknown turn " + turn);
    }
  }
}
