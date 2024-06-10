package com.shepherdjerred.capstone.ai.genetic;

import com.google.common.collect.ImmutableSet;
import com.shepherdjerred.capstone.ai.QuoridorAi;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.PruningAlphaBetaQuoridorAi;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.rules.PieceDistanceNodePruningRule;
import com.shepherdjerred.capstone.ai.alphabeta.pruning.rules.RandomDiscardNodePruningRule;
import com.shepherdjerred.capstone.ai.evaluator.EvaluatorWeights;
import com.shepherdjerred.capstone.ai.evaluator.WeightedMatchEvaluator;
import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchSettings;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.logic.turn.Turn;
import io.jenetics.DoubleChromosome;
import io.jenetics.DoubleGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Problem;
import io.jenetics.util.DoubleRange;
import java.util.function.Function;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WeightsProblem implements Problem<EvaluatorWeights, DoubleGene, Integer> {

  @Override
  public Function<EvaluatorWeights, Integer> fitness() {
    return weights -> {
      var boardSettings = new BoardSettings(9, PlayerCount.TWO);
      var matchSettings = new MatchSettings(10, QuoridorPlayer.ONE, PlayerCount.TWO);
      var match = Match.from(matchSettings, boardSettings);

      var weightedEvaluator = new WeightedMatchEvaluator(weights);
      var constantWeights = new EvaluatorWeights(
          9612.407041694314,
          -7288.691596308785,
          9786.056427421212,
          2396.69915479313,
          476.91303038346996
      );
      var constantlyWeightedEvaluator = new WeightedMatchEvaluator(constantWeights);

      var pruningRules = ImmutableSet.of(
          new RandomDiscardNodePruningRule(60),
          new PieceDistanceNodePruningRule(3));

      var alphaBetaAi = new PruningAlphaBetaQuoridorAi(weightedEvaluator, 2, pruningRules);

      var randomAi = new PruningAlphaBetaQuoridorAi(constantlyWeightedEvaluator, 2, pruningRules);

      return simulateAi(match, alphaBetaAi, randomAi);
    };
  }

  @Override
  public Codec<EvaluatorWeights, DoubleGene> codec() {
    return Codec.of(
        Genotype.of(DoubleChromosome.of(DoubleRange.of(-10000, 10000), 5)),
        gt -> new EvaluatorWeights(gt.get(0, 0).doubleValue(),
            gt.get(0, 1).doubleValue(),
            gt.get(0, 2).doubleValue(),
            gt.get(0, 3).doubleValue(),
            gt.get(0, 4).doubleValue())
    );
  }

  private int simulateAi(Match match, QuoridorAi playerOne, QuoridorAi playerTwo) {
    log.info("Simulating AI match");

    int currentTurn = 1;
    while (match.getMatchStatus().getStatus() == Status.IN_PROGRESS) {

      Turn aiTurn;
      if (match.getActivePlayerId() == QuoridorPlayer.ONE) {
        aiTurn = playerOne.calculateBestTurn(match);
      } else {
        aiTurn = playerTwo.calculateBestTurn(match);
      }

      match = match.doTurn(aiTurn);

      currentTurn++;

      if (currentTurn > 200) {
        return Integer.MIN_VALUE;
      }

      if (currentTurn < 10) {
        log.info("TURN: " + currentTurn);
      } else if (currentTurn % 10 == 0 && currentTurn < 100) {
        log.info("TURN: " + currentTurn);
      } else if (currentTurn % 50 == 0) {
        log.info("TURN: " + currentTurn);
      }
    }

    log.info(match.getMatchStatus().getVictor());

    if (match.getMatchStatus().getVictor() == QuoridorPlayer.ONE) {
      return currentTurn * -1;
    } else {
      return Integer.MIN_VALUE;
    }
  }
}
