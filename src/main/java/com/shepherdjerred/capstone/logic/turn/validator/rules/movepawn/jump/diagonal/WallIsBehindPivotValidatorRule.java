package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.jump.diagonal;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.JumpPawnDiagonalTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import com.shepherdjerred.capstone.logic.util.Direction;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WallIsBehindPivotValidatorRule implements ValidatorRule<JumpPawnDiagonalTurn> {

  @Override
  public TurnValidationResult validate(Match match, JumpPawnDiagonalTurn turn) {
    var board = match.board();
    var src = turn.source();
    var dest = turn.destination();
    var pivot = turn.pivot();

    if (board.isCoordinateInvalid(src) || board.isCoordinateInvalid(dest)
        || board.isCoordinateInvalid(pivot)) {
      return new TurnValidationResult(ErrorMessage.COULD_NOT_RUN_VALIDATOR);
    }

    Direction direction = null;

    if (pivot.x() == dest.x()) {
      if (src.y() - dest.y() > 0) {
        direction = Direction.RIGHT;
      } else if (src.y() - dest.y() < 0) {
        direction = Direction.LEFT;
      } else {
        throw new UnsupportedOperationException();
      }
    }

    if (pivot.y() == dest.y()) {
      if (src.x() - dest.x() < 0) {
        direction = Direction.DOWN;
      } else if (src.x() - dest.x() > 0) {
        direction = Direction.UP;
      } else {
        throw new UnsupportedOperationException();
      }
    }

    Coordinate coordinateToCheck;

    if (direction == Direction.UP) {
      coordinateToCheck = pivot.above();
    } else if (direction == Direction.DOWN) {
      coordinateToCheck = pivot.below();
    } else if (direction == Direction.LEFT) {
      coordinateToCheck = pivot.toLeft();
    } else if (direction == Direction.RIGHT) {
      coordinateToCheck = pivot.toRight();
    } else {
      throw new UnsupportedOperationException();
    }

    if (board.isCoordinateInvalid(coordinateToCheck)) {
      log.debug("No wall behind pivot {} {}", turn, direction);
      return new TurnValidationResult(ErrorMessage.NO_WALL_BEHIND_PIVOT);
    }

    if (match.board().hasWall(coordinateToCheck)) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.NO_WALL_BEHIND_PIVOT);
    }
  }
}
