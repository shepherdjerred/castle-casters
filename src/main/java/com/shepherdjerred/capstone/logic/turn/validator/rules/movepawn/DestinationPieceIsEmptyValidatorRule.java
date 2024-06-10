package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import lombok.ToString;

@ToString
public class DestinationPieceIsEmptyValidatorRule implements ValidatorRule<MovePawnTurn> {

  @Override
  public TurnValidationResult validate(Match match, MovePawnTurn turn) {
    var destination = turn.getDestination();
    if (match.getBoard().isEmpty(destination)) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.DESTINATION_NOT_EMPTY);
    }
  }
}
