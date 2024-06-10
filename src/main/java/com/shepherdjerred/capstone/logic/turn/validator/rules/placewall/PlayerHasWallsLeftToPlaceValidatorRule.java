package com.shepherdjerred.capstone.logic.turn.validator.rules.placewall;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;

public class PlayerHasWallsLeftToPlaceValidatorRule implements ValidatorRule<PlaceWallTurn> {

  @Override
  public TurnValidationResult validate(Match match, PlaceWallTurn turn) {
    var player = turn.causer();
    if (match.getWallsLeft(player) > 0) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.PLAYER_HAS_NO_WALLS);
    }
  }
}
