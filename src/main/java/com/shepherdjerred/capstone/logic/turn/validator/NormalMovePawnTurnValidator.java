package com.shepherdjerred.capstone.logic.turn.validator;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.NormalMovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.normal.DestinationIsOnePawnSpaceAwayValidatorRule;
import com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn.normal.WallIsNotBetweenSourceAndDestinationValidatorRule;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NormalMovePawnTurnValidator implements TurnValidator<NormalMovePawnTurn> {

  private final Set<ValidatorRule<NormalMovePawnTurn>> rules;

  public NormalMovePawnTurnValidator() {
    rules = new HashSet<>();
    rules.add(new DestinationIsOnePawnSpaceAwayValidatorRule());
    rules.add(new WallIsNotBetweenSourceAndDestinationValidatorRule());
  }

  @Override
  public TurnValidationResult validate(Match match, NormalMovePawnTurn turn) {
    var movePawnValidator = new MovePawnTurnValidator();

    var movePawnResult = movePawnValidator.validate(match, turn);
    var result = new TurnValidationResult();
    for (ValidatorRule<NormalMovePawnTurn> rule : rules) {
      var ruleResult = rule.validate(match, turn);
      result = TurnValidationResult.combine(result, ruleResult);
    }

    return TurnValidationResult.combine(result, movePawnResult);
  }

}
