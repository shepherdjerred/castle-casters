package com.shepherdjerred.capstone.ai.evaluator.rules;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import static com.shepherdjerred.capstone.ai.evaluator.MatchEvaluator.MIN_SCORE;

@Log4j2
@ToString
public class DefeatEvaluatorRule implements EvaluatorRule {

  @Override
  public double evaluate(Match match, QuoridorPlayer player) {
    if (match.matchStatus().status() == Status.VICTORY
        && match.matchStatus().victor() != player) {
//      log.info("EVAL DEFEAT; WINNER: " + match.getMatchStatus().getVictor() + " NOT "
//          + player);
      return MIN_SCORE;
    } else {
      return 0.0;
    }
  }
}
