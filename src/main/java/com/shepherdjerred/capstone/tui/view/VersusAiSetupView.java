package com.shepherdjerred.capstone.tui.view;

import com.shepherdjerred.capstone.ai.QuoridorAi;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.PruningAlphaBetaQuoridorAi;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.rules.DeepWallNodePruningRule;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.rules.NodePruningRule;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.rules.PieceDistanceNodePruningRule;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.rules.RandomDiscardNodePruningRule;
import com.shepherdjerred.capstone.ai.evaluator.EvaluatorWeights;
import com.shepherdjerred.capstone.ai.evaluator.MatchEvaluator;
import com.shepherdjerred.capstone.ai.evaluator.WeightedMatchEvaluator;
import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.MatchSettings;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class VersusAiSetupView implements View {

  private final Scanner scanner;

  @Override
  public Optional<View> display() {
    boolean shouldContinue = true;
    while (shouldContinue) {
      QuoridorPlayer startingPlayer;
      QuoridorPlayer aiPlayer;

      System.out.println("Starting player");
      var input = scanner.next();

      try {
        var startingPlayerInt = Integer.parseInt(input);
        startingPlayer = QuoridorPlayer.fromInt(startingPlayerInt);
      } catch (Exception e) {
        log.error("Error parsing starting player", e);
        continue;
      }

      System.out.println("AI player");
      input = scanner.next();

      try {
        var aiPlayerInt = Integer.parseInt(input);
        aiPlayer = QuoridorPlayer.fromInt(aiPlayerInt);
      } catch (Exception e) {
        log.error("Error parsing ai player", e);
        continue;
      }

      BoardSettings boardSettings = new BoardSettings(9, PlayerCount.TWO);
      MatchSettings matchSettings = new MatchSettings(10, startingPlayer, PlayerCount.TWO);

      EvaluatorWeights evaluatorWeights = new EvaluatorWeights(
          2293.7109999771455,
          398.7527547140071,
          4762.159725078656,
          9407.150981288025,
          -6985.356279833557
      );

      MatchEvaluator matchEvaluator = new WeightedMatchEvaluator(evaluatorWeights);

      Set<NodePruningRule> pruningRules = new HashSet<>();
      pruningRules.add(new RandomDiscardNodePruningRule(50));
      pruningRules.add(new DeepWallNodePruningRule(2));
      pruningRules.add(new PieceDistanceNodePruningRule(3));

      QuoridorAi quoridorAi = new PruningAlphaBetaQuoridorAi(matchEvaluator, 4, pruningRules);

      return Optional.of(new PlayerVersusAiView(scanner,
          boardSettings,
          matchSettings,
          quoridorAi,
          aiPlayer));
    }

    return Optional.of(new MainMenuView(scanner));
  }
}
