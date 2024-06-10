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
    var board = match.getBoard();
    var src = turn.getSource();
    var dest = turn.getDestination();
    var pivot = turn.getPivot();

    if (board.isCoordinateInvalid(src) || board.isCoordinateInvalid(dest)
        || board.isCoordinateInvalid(pivot)) {
      return new TurnValidationResult(ErrorMessage.COULD_NOT_RUN_VALIDATOR);
    }

    Direction direction = null;

    if (pivot.getX() == dest.getX()) {
      if (src.getY() - dest.getY() > 0) {
        direction = Direction.RIGHT;
      } else if (src.getY() - dest.getY() < 0) {
        direction = Direction.LEFT;
      } else {
        throw new UnsupportedOperationException();
      }
    }

    if (pivot.getY() == dest.getY()) {
      if (src.getX() - dest.getX() < 0) {
        direction = Direction.DOWN;
      } else if (src.getX() - dest.getX() > 0) {
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
      log.debug("No wall behind pivot " + turn + " " + direction);
      return new TurnValidationResult(ErrorMessage.NO_WALL_BEHIND_PIVOT);
    }

    if (match.getBoard().hasWall(coordinateToCheck)) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.NO_WALL_BEHIND_PIVOT);
    }
  }
}
