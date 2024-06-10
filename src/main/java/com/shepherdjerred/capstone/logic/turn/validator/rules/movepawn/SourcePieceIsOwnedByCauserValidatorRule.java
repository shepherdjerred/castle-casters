package com.shepherdjerred.capstone.logic.turn.validator.rules.movepawn;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidationResult.ErrorMessage;
import com.shepherdjerred.capstone.logic.turn.validator.rules.ValidatorRule;
import lombok.ToString;

@ToString
public class SourcePieceIsOwnedByCauserValidatorRule implements ValidatorRule<MovePawnTurn> {

  @Override
  public TurnValidationResult validate(Match match, MovePawnTurn turn) {
    var source = turn.source();
    var mover = turn.causer();
    var sourcePieceOwner = match.board().getPiece(source).owner();

    if (mover == sourcePieceOwner) {
      return new TurnValidationResult();
    } else {
      return new TurnValidationResult(ErrorMessage.SOURCE_PIECE_NOT_OWNED_BY_PLAYER);
    }
  }
}
