package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.normal;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.NormalMovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import lombok.ToString;

@ToString
public class DestinationIsOnePawnSpaceAwayValidatorRule implements ValidatorRule<NormalMovePawnTurn> {

  @Override
  public TurnValidationResult validate(Match match, NormalMovePawnTurn turn) {
    var dist = Coordinate.calculateManhattanDistance(turn.getSource(), turn.getDestination());

    // We check if the distance equals two because wall cells count in the calculation
    if (dist == 2) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.MOVE_NOT_ONE_SPACE_AWAY);
    }
  }
}
