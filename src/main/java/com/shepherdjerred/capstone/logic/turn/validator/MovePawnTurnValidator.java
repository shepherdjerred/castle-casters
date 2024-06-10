package com.shepherdjerred.capstone.logic.turn.validator;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.DestinationBoardCellTypeIsPawnValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.DestinationCoordinateIsValidValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.DestinationPieceIsEmptyValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.MoveIsCardinalValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.SourceAndDestinationAreDifferentValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.SourceBoardCellTypeIsPawnValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.SourceCoordinateIsValidValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.SourcePieceIsOwnedByCauserValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.SourcePieceTypeIsPawnValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.TurnSourceIsSameAsActualLocationValidatorRule;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MovePawnTurnValidator implements TurnValidator<MovePawnTurn> {

  private final Set<ValidatorRule<MovePawnTurn>> rules;

  public MovePawnTurnValidator() {
    rules = new HashSet<>();
    rules.add(new DestinationBoardCellTypeIsPawnValidatorRule());
    rules.add(new DestinationCoordinateIsValidValidatorRule());
    rules.add(new DestinationPieceIsEmptyValidatorRule());
    rules.add(new MoveIsCardinalValidatorRule());
    rules.add(new SourceAndDestinationAreDifferentValidatorRule());
    rules.add(new SourceBoardCellTypeIsPawnValidatorRule());
    rules.add(new SourceCoordinateIsValidValidatorRule());
    rules.add(new SourcePieceIsOwnedByCauserValidatorRule());
    rules.add(new SourcePieceTypeIsPawnValidatorRule());
    rules.add(new TurnSourceIsSameAsActualLocationValidatorRule());
  }

  @Override
  public TurnValidationResult validate(Match match, MovePawnTurn turn) {
    var result = new TurnValidationResult();
    for (ValidatorRule<MovePawnTurn> rule : rules) {
      var ruleResult = rule.validate(match, turn);
      result = TurnValidationResult.combine(result, ruleResult);
    }
    return result;
  }

}
