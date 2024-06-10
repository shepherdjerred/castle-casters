package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.normal;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.NormalMovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import lombok.ToString;

@ToString
public class WallIsNotBetweenSourceAndDestinationValidatorRule implements ValidatorRule<NormalMovePawnTurn> {

  @Override
  public TurnValidationResult validate(Match match, NormalMovePawnTurn turn) {
    var dist = Coordinate.calculateManhattanDistance(turn.getSource(), turn.getDestination());

    // If the distance != 2 then we can't do this check
    if (dist != 2) {
      return new TurnValidationResult(ErrorMessage.COULD_NOT_RUN_VALIDATOR);
    }

    var coordinateBetween = Coordinate.calculateMidpoint(turn.getSource(), turn.getDestination());
    if (match.getBoard().hasPiece(coordinateBetween)) {
      return new TurnValidationResult(ErrorMessage.WALL_BETWEEN_SOURCE_AND_DESTINATION);
    } else {
      return new TurnValidationResult();
    }
  }
}
