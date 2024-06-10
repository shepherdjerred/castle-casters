package com.shepherdjerred.capstone.ai.evaluator;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.Random;

public class RandomMatchEvaluator implements MatchEvaluator {

  private final Random random = new Random();

  @Override
  public double evaluateMatch(Match match, QuoridorPlayer playerId) {
    double max = 10000;
    double min = -10000;
    var randomOffset = min + (max - min) * random.nextDouble();
    return randomOffset
        + getScoreForDefeat(match, playerId)
        + getScoreForVictory(match, playerId);
  }

  private double getScoreForDefeat(Match match, QuoridorPlayer playerId) {
    if (match.getMatchStatus().getStatus() == Status.VICTORY
        && match.getMatchStatus().getVictor() != playerId) {
      return MIN_SCORE;
    } else {
      return 0;
    }
  }

  private double getScoreForVictory(Match match, QuoridorPlayer playerId) {
    if (match.getMatchStatus().getVictor() == playerId) {
      return MAX_SCORE;
    } else {
      return 0;
    }
  }
}
