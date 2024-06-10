package com.shepherdjerred.capstone.ai.evaluator.rules;

import com.shepherdjerred.capstone.logic.board.search.BoardSearch;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.PlayerGoals;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class OpponentsShortestPathEvaluatorRule implements EvaluatorRule {

  private final BoardSearch boardSearch;
  private final PlayerGoals playerGoals;

  @Override
  public double evaluate(Match match, QuoridorPlayer player) {
    var playerCount = match.getMatchSettings().getPlayerCount();
    Set<QuoridorPlayer> players = new HashSet<>();

    if (playerCount == PlayerCount.TWO) {
      players.add(QuoridorPlayer.ONE);
      players.add(QuoridorPlayer.TWO);
    } else if (playerCount == PlayerCount.FOUR) {
      players.add(QuoridorPlayer.ONE);
      players.add(QuoridorPlayer.TWO);
      players.add(QuoridorPlayer.THREE);
      players.add(QuoridorPlayer.FOUR);
    } else {
      throw new UnsupportedOperationException();
    }

    players.remove(player);

    var sumOfDistances = players.stream()
        .map(p -> {
          var gridSize = match.getBoard().getGridSize();
          var goals = playerGoals.getGoalCoordinatesForPlayer(p,
              gridSize);
          return boardSearch.getShortestPathToAnyDestination(match.getBoard(),
              match.getBoard().getPawnLocation(p),
              goals);
        })
        .mapToInt(Integer::intValue)
        .sum();

    return Math.pow(sumOfDistances, 2);
  }
}
