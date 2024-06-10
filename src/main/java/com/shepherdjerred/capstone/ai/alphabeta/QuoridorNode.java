package com.shepherdjerred.capstone.ai.alphabeta;

import com.github.bentorfs.ai.common.TreeNode;
import com.shepherdjerred.capstone.ai.evaluator.MatchEvaluator;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.logic.turn.generator.TurnGenerator;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidatorFactory;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@ToString(exclude = {"match", "matchEvaluator"})
@AllArgsConstructor
public class QuoridorNode implements IQuoridorNode {

  private final QuoridorPlayer optimizingPlayer;
  private final Match match;
  private final Turn turn;
  private final MatchEvaluator matchEvaluator;

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
    return match.getMatchStatus().getStatus() == Status.VICTORY;
  }

  @Override
  public double getValue() {
    return matchEvaluator.evaluateMatch(match, optimizingPlayer);
  }
}
