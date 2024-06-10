package com.shepherdjerred.capstone.ai.evaluator.rules;

import com.shepherdjerred.capstone.logic.board.search.BoardSearch;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.PlayerGoals;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@AllArgsConstructor
public class ShortestPathEvaluatorRule implements EvaluatorRule {

  private final BoardSearch boardSearch;
  private final PlayerGoals playerGoals;

  @Override
  public double evaluate(Match match, QuoridorPlayer player) {
    var maxDistance = match.getBoard().getBoardSize() * 2;
    var goals = playerGoals.getGoalCoordinatesForPlayer(player,
        match.getBoard().getGridSize());
//    log.info(new MatchFormatter().matchToString(match));
    return maxDistance - boardSearch.getShortestPathToAnyDestination(match.getBoard(),
        match.getBoard().getPawnLocation(player),
        goals);
  }
}
