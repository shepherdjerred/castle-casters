package com.shepherdjerred.capstone.logic.turn.validator;

import com.shepherdjerred.capstone.logic.turn.*;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TurnValidatorFactory {

  public <T extends Turn> TurnValidator<T> getValidator(T turn) {
    TurnValidator<?> validator = switch (turn) {
      case PlaceWallTurn placeWallTurn -> new PlaceWallTurnValidator();
      case NormalMovePawnTurn normalMovePawnTurn -> new NormalMovePawnTurnValidator();
      case JumpPawnStraightTurn jumpPawnStraightTurn -> new JumpPawnStraightTurnValidator();
      case JumpPawnDiagonalTurn jumpPawnDiagonalTurn -> new JumpPawnDiagonalTurnValidator();
      case null, default -> throw new UnsupportedOperationException();
    };

    //noinspection unchecked
    return (TurnValidator<T>) validator;
  }
}
