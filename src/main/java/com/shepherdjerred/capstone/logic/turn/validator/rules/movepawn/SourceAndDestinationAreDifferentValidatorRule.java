package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import lombok.ToString;

@ToString
public class SourceAndDestinationAreDifferentValidatorRule implements ValidatorRule<MovePawnTurn> {

  @Override
  public TurnValidationResult validate(Match match, MovePawnTurn turn) {
    if (turn.source().equals(turn.destination())) {
      return new TurnValidationResult(ErrorMessage.SOURCE_AND_DESTINATION_NOT_DIFFERENT);
    } else {
      return new TurnValidationResult();
    }
  }
}
