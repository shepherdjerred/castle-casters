package com.shepherdjerred.capstone.ai.alphabeta;

import com.github.bentorfs.ai.search.minimax.MiniMaxAlgorithm;
import com.shepherdjerred.capstone.ai.QuoridorAi;
import com.shepherdjerred.capstone.ai.evaluator.MatchEvaluator;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlphaBetaQuoridorAi implements QuoridorAi {

  private final MatchEvaluator matchEvaluator;
  private final int depth;

  @Override
  public Turn calculateBestTurn(Match match) {
    var current = new QuoridorNode(match.getActivePlayerId(), match, null, matchEvaluator);
    var bestNode = (QuoridorNode) new MiniMaxAlgorithm(depth).getBestMove(current);
    return bestNode.getTurn();
  }
}
