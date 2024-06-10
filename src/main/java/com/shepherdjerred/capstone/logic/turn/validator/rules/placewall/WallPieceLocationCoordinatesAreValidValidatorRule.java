package com.shepherdjerred.capstone.logic.turn.validator.rules.placewall;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;

public class WallPieceLocationCoordinatesAreValidValidatorRule implements ValidatorRule<PlaceWallTurn> {

  @Override
  public TurnValidationResult validate(Match match, PlaceWallTurn turn) {
    var location = turn.getLocation();
    var c1 = location.getFirstCoordinate();
    var vertex = location.getVertex();
    var c2 = location.getSecondCoordinate();
    var board = match.getBoard();
    if (board.isCoordinateInvalid(c1)
        || board.isCoordinateInvalid(vertex)
        || board.isCoordinateInvalid(c2)) {
      return new TurnValidationResult(ErrorMessage.COORDINATES_INVALID);
    } else {
      return new TurnValidationResult();
    }
  }
}
