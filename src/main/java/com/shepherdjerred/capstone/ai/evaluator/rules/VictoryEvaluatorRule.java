package com.shepherdjerred.capstone.ai.evaluator.rules;

import static com.shepherdjerred.capstone.ai.evaluator.MatchEvaluator.MAX_SCORE;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
public class VictoryEvaluatorRule implements EvaluatorRule {

  @Override
  public double evaluate(Match match, QuoridorPlayer player) {
    if (match.getMatchStatus().getVictor() == player) {
//      log.info("WINNER");
      return MAX_SCORE;
    } else {
      return 0.0;
    }
  }
}
