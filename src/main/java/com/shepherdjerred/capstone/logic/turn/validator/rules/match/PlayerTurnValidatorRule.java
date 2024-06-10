package com.shepherdjerred.capstone.logic.turn.validator.rules.match;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;

public class PlayerTurnValidatorRule implements ValidatorRule<Turn> {

  @Override
  public TurnValidationResult validate(Match match, Turn turn) {
    if (turn.getCauser() == match.getActivePlayerId()) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.NOT_CAUSERS_TURN);
    }
  }
}
