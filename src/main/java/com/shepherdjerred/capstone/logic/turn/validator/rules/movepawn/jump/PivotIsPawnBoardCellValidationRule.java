package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.jump;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.JumpPawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;

public class PivotIsPawnBoardCellValidationRule implements ValidatorRule<JumpPawnTurn> {

  @Override
  public TurnValidationResult validate(Match match, JumpPawnTurn turn) {
    var pivot = turn.getPivot();

    if (match.getBoard().isCoordinateInvalid(pivot)) {
      return new TurnValidationResult(true);
    }

    if (match.getBoard().isPawnBoardCell(pivot)) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.PIVOT_CELL_TYPE_NOT_PAWN);
    }
  }
}
