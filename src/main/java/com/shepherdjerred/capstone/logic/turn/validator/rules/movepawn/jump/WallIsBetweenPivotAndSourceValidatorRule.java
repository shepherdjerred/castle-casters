package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.jump;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.JumpPawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;

public class WallIsBetweenPivotAndSourceValidatorRule implements ValidatorRule<JumpPawnTurn> {

  @Override
  public TurnValidationResult validate(Match match, JumpPawnTurn turn) {
    var pivot = turn.getPivot();
    var src = turn.getSource();
    var dist = Coordinate.calculateManhattanDistance(pivot, src);

    // If the distance != 2 then we can't do this check
    if (dist != 2) {
      return new TurnValidationResult(ErrorMessage.COULD_NOT_RUN_VALIDATOR);
    }

    var coordinateBetween = Coordinate.calculateMidpoint(pivot, src);
    if (match.getBoard().hasPiece(coordinateBetween)) {
      return new TurnValidationResult(ErrorMessage.WALL_BETWEEN_PIVOT_AND_SOURCE);
    } else {
      return new TurnValidationResult();
    }
  }
}
