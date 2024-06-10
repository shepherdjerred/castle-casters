package com.shepherdjerred.capstone.ai.alphabeta.pruning;

import com.github.bentorfs.ai.common.TreeNode;
import com.shepherdjerred.capstone.ai.alphabeta.IQuoridorNode;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.rules.NodePruningRule;
import com.shepherdjerred.capstone.ai.evaluator.MatchEvaluator;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.logic.turn.generator.TurnGenerator;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidatorFactory;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@ToString(exclude = {"match", "matchEvaluator"})
@AllArgsConstructor
public class PruningQuoridorNode implements IQuoridorNode {

  private final QuoridorPlayer optimizingPlayer;
  private final Match match;
  private final Turn turn;
  private final MatchEvaluator matchEvaluator;
  private final Set<NodePruningRule> nodePruningRules;
  private final int currentDepth;
  private int childrenCount;

  @Override
  public Collection<TreeNode> getChildNodes() {
    var turnGenerator = new TurnGenerator(new TurnValidatorFactory());
    var possibleTurns = turnGenerator.generateValidTurns(match);

    Set<TreeNode> children = possibleTurns.stream()
        .map(turn -> {
          var newMatchState = match.doTurnUnchecked(turn);
          QuoridorPlayer nextOptimizingPlayer;

          if (currentDepth == 0) {
            nextOptimizingPlayer = optimizingPlayer;
          } else {
            nextOptimizingPlayer = newMatchState.getActivePlayerId();
          }

          return new PruningQuoridorNode(nextOptimizingPlayer,
              newMatchState,
              turn,
              matchEvaluator,
              nodePruningRules,
              currentDepth + 1,
              0);
        })
        .filter(node -> nodePruningRules.stream().noneMatch(rule -> rule.shouldPrune(node)))
        .collect(Collectors.toSet());

    this.childrenCount = children.size();
    log.info(String.format("Depth: %s, Children %s", currentDepth, childrenCount));

    return children;
  }

  @Override
  public boolean isSolutionNode() {
    return match.getMatchStatus().getStatus() == Status.VICTORY;
  }

  @Override
  public double getValue() {
//    log.info("OPTIMIZING FOR " + optimizingPlayer + " AP: " + match.getActiveQuoridorPlayer() + " DEPTH: " + getCurrentDepth());
    return matchEvaluator.evaluateMatch(match, optimizingPlayer);
  }
}
