package com.shepherdjerred.capstone.logic.turn.validator.rules.placewall;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;

public class WallPieceLocationCoordinatesAreFreeValidatorRule implements ValidatorRule<PlaceWallTurn> {

  @Override
  public TurnValidationResult validate(Match match, PlaceWallTurn turn) {
    var board = match.board();
    var location = turn.location();
    var firstCoordinate = location.firstCoordinate();
    var secondCoordinate = location.secondCoordinate();

    if (board.isEmpty(firstCoordinate)
        && board.isEmpty(secondCoordinate)) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.COORDINATES_NOT_EMPTY);
    }
  }
}
