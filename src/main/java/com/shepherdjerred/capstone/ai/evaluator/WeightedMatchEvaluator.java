package com.shepherdjerred.capstone.ai.evaluator;

import com.shepherdjerred.capstone.ai.evaluator.rules.AdjacentPawnsEvaluationRule;
import com.shepherdjerred.capstone.ai.evaluator.rules.DefeatEvaluatorRule;
import com.shepherdjerred.capstone.ai.evaluator.rules.EvaluatorRule;
import com.shepherdjerred.capstone.ai.evaluator.rules.OpponentsShortestPathEvaluatorRule;
import com.shepherdjerred.capstone.ai.evaluator.rules.RemainingWallsEvaluatorRule;
import com.shepherdjerred.capstone.ai.evaluator.rules.ShortestPathEvaluatorRule;
import com.shepherdjerred.capstone.ai.evaluator.rules.VictoryEvaluatorRule;
import com.shepherdjerred.capstone.ai.evaluator.rules.WallsNearbyEvaluationRule;
import com.shepherdjerred.capstone.logic.board.search.AStarBoardSearch;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.PlayerGoals;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@AllArgsConstructor
public class WeightedMatchEvaluator implements MatchEvaluator {

  private final EvaluatorWeights weights;

  @Override
  public double evaluateMatch(Match match, QuoridorPlayer optimizingPlayer) {
    Map<EvaluatorRule, Double> evaluators = new HashMap<>();
    evaluators.put(new ShortestPathEvaluatorRule(new AStarBoardSearch(), new PlayerGoals()),
        weights.getShortestPathWeight());
    evaluators.put(new DefeatEvaluatorRule(), 1.0);
    evaluators.put(new AdjacentPawnsEvaluationRule(), weights.getAdjacentPawnsWeight());
    evaluators.put(new OpponentsShortestPathEvaluatorRule(new AStarBoardSearch(),
        new PlayerGoals()), weights.getOpponentsShortestPathWeight());
    evaluators.put(new RemainingWallsEvaluatorRule(), weights.getRemainingWallsWeight());
    evaluators.put(new VictoryEvaluatorRule(), 1.0);
    evaluators.put(new WallsNearbyEvaluationRule(), weights.getWallsNearbyWeight());

    var matchScore = evaluators.entrySet().stream()
        .map(entry -> {
          var evaluator = entry.getKey();
          var weight = entry.getValue();
          var rawScore = evaluator.evaluate(match, optimizingPlayer);
          var weightedScore = rawScore * weight;
//          log.debug(evaluator + " RAW: " + rawScore);
//          log.debug(evaluator + " WEIGHTED: " + weightedScore);
          return weightedScore;
        })
        .mapToDouble(Double::doubleValue)
        .sum();

//    log.debug("Match score: " + matchScore);
    return matchScore;
  }

}
