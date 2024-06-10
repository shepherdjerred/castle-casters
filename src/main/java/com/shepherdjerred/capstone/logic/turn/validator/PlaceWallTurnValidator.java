package com.shepherdjerred.capstone.logic.turn.validator;

import com.shepherdjerred.capstone.logic.board.search.AStarBoardSearch;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.PlayerGoals;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.placewall.PlayerHasWallsLeftToPlaceValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.placewall.WallDoesntBlockPawnsValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.placewall.WallPieceLocationCoordinatesAreFreeValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.placewall.WallPieceLocationCoordinatesAreValidValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.placewall.WallPieceLocationCoordinatesAreWallBoardCellsValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.placewall.WallPieceLocationVertexIsFreeValidatorRule;
import java.util.HashSet;
import java.util.Set;

public class PlaceWallTurnValidator implements TurnValidator<PlaceWallTurn> {

  private final Set<ValidatorRule<PlaceWallTurn>> rules;

  public PlaceWallTurnValidator() {
    rules = new HashSet<>();
    rules.add(new PlayerHasWallsLeftToPlaceValidatorRule());
    rules.add(new WallPieceLocationCoordinatesAreFreeValidatorRule());
    rules.add(new WallPieceLocationCoordinatesAreValidValidatorRule());
    rules.add(new WallPieceLocationCoordinatesAreWallBoardCellsValidatorRule());
    rules.add(new WallPieceLocationVertexIsFreeValidatorRule());
    rules.add(new WallPieceLocationCoordinatesAreValidValidatorRule());
  }

  @Override
  public TurnValidationResult validate(Match match, PlaceWallTurn turn) {
    var result = new TurnValidationResult();
    for (ValidatorRule<PlaceWallTurn> rule : rules) {
      var ruleResult = rule.validate(match, turn);
      result = TurnValidationResult.combine(result, ruleResult);
    }

    if (result.isError()) {
      return result;
    } else {
      var blockResult = new WallDoesntBlockPawnsValidatorRule(new AStarBoardSearch(),
          new PlayerGoals()).validate(match, turn);
      return TurnValidationResult.combine(result, blockResult);
    }
  }

}
