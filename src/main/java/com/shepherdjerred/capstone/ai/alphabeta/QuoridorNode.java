package com.shepherdjerred.capstone.ai.alphabeta;

import com.github.bentorfs.ai.common.TreeNode;
import com.shepherdjerred.capstone.ai.evaluator.MatchEvaluator;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.logic.turn.generator.TurnGenerator;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidatorFactory;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;
import java.util.stream.Collectors;

@Log4j2
public record QuoridorNode(QuoridorPlayer optimizingPlayer, Match match, Turn turn,
                           MatchEvaluator matchEvaluator) implements IQuoridorNode {

  @Override
  public Collection<TreeNode> getChildNodes() {
    var turnGenerator = new TurnGenerator(new TurnValidatorFactory());
    var possibleTurns = turnGenerator.generateValidTurns(match);

    return possibleTurns.stream()
        .map(turn -> {
//          System.out.println(turn);
          var newMatchState = match.doTurnUnchecked(turn);
          return new QuoridorNode(newMatchState.getActivePlayerId(), newMatchState, turn, matchEvaluator);
        })
        .collect(Collectors.toSet());
  }

  @Override
  public boolean isSolutionNode() {
    return match.matchStatus().status() == Status.VICTORY;
  }

  @Override
  public double getValue() {
    return matchEvaluator.evaluateMatch(match, optimizingPlayer);
  }
}
