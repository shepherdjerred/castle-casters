package com.shepherdjerred.capstone.ai.evaluator.rules;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.ToString;

@ToString
public class AdjacentPawnsEvaluationRule implements EvaluatorRule {

  @Override
  public double evaluate(Match match, QuoridorPlayer player) {
    var board = match.board();
    var pawnLocation = board.getPawnLocation(player);
    var adjacentPawnLocations = board.getPawnSpacesAdjacentToPawnSpace(pawnLocation);
    return adjacentPawnLocations.stream().filter(board::hasPiece).count();
  }
}
