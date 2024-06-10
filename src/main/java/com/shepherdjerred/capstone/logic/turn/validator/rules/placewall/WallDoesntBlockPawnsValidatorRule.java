package com.shepherdjerred.capstone.logic.turn.validator.rules.placewall;

import com.shepherdjerred.capstone.logic.board.search.BoardSearch;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.PlayerGoals;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2

@AllArgsConstructor
public class WallDoesntBlockPawnsValidatorRule implements ValidatorRule<PlaceWallTurn> {

  private final BoardSearch boardSearch;
  private final PlayerGoals playerGoals;

  @Override
  public TurnValidationResult validate(Match match, PlaceWallTurn turn) {
    var board = match.board().placeWall(turn.causer(), turn.location());
    var gridSize = board.getGridSize();
    var pawnLocations = board.getPawnLocations();

    var isAnyPawnBlocked = pawnLocations.stream()
        .anyMatch(pawnLocation -> {
          var player = match.board().getPiece(pawnLocation).owner();
          var goals = playerGoals.getGoalCoordinatesForPlayer(player, gridSize);
          var pawnHasPathToGoal = boardSearch.hasPathToAnyDestination(board, pawnLocation, goals);
          return !pawnHasPathToGoal;
        });

    if (isAnyPawnBlocked) {
      return new TurnValidationResult(ErrorMessage.WALL_BLOCKS_PAWN_PATH);
    } else {
      return new TurnValidationResult();
    }
  }
}
