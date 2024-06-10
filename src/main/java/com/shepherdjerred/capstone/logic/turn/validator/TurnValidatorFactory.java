package com.shepherdjerred.capstone.logic.turn.validator;

import com.shepherdjerred.capstone.logic.turn.JumpPawnDiagonalTurn;
import com.shepherdjerred.capstone.logic.turn.JumpPawnStraightTurn;
import com.shepherdjerred.capstone.logic.turn.NormalMovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TurnValidatorFactory {

  public <T extends Turn> TurnValidator<T> getValidator(T turn) {
    TurnValidator validator;

    if (turn instanceof PlaceWallTurn) {
      validator = new PlaceWallTurnValidator();
    } else if (turn instanceof NormalMovePawnTurn) {
      validator = new NormalMovePawnTurnValidator();
    } else if (turn instanceof JumpPawnStraightTurn) {
      validator = new JumpPawnStraightTurnValidator();
    } else if (turn instanceof JumpPawnDiagonalTurn) {
      validator = new JumpPawnDiagonalTurnValidator();
    } else {
      throw new UnsupportedOperationException();
    }

    return validator;
  }
}
