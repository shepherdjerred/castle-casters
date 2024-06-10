package com.shepherdjerred.capstone.logic.turn.validator.rules.placewall;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;

public class WallPieceLocationVertexIsVertexBoardCellValidatorRule implements ValidatorRule<PlaceWallTurn> {

  @Override
  public TurnValidationResult validate(Match match, PlaceWallTurn turn) {
    var location = turn.location();
    var vertex = location.vertex();
    var board = match.board();
    if (board.isWallVertexBoardCell(vertex)) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.VERTEX_COORDINATE_IS_NOT_VERTEX_CELL);
    }
  }
}
