package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.turn.NormalMovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class MatchStatusUpdater {

  private final PlayerGoals playerGoals;

  public MatchStatus updateMatchStatus(Turn turn, Match match) {
    var player = turn.causer();
    if (turn instanceof NormalMovePawnTurn movePawnTurn) {
      var gridSize = match.board().getGridSize();
      var destination = movePawnTurn.destination();

      var goals = playerGoals.getGoalCoordinatesForPlayer(player, gridSize);
      if (goals.contains(destination)) {
        return new MatchStatus(player, Status.VICTORY);
      }
    }
//    if (match.getMatchHistory().getSize() >= 5 && doesStalemateRuleApply(match)) {
//      return new MatchStatus(QuoridorPlayer.NULL, Status.STALEMATE);
//    }
    return match.matchStatus();
  }

  private boolean doesStalemateRuleApply(Match match) {
    var curr = match;
    var prev1 = curr.matchHistory().pop().match();
    var prev2 = prev1.matchHistory().pop().match();
    var prev3 = prev2.matchHistory().pop().match();
    var prev4 = prev3.matchHistory().pop().match();
    var prev5 = prev4.matchHistory().pop().match();

    return curr.equals(prev2) && prev2.equals(prev4) && prev1.equals(prev3) && prev3.equals(prev5);
  }
}
