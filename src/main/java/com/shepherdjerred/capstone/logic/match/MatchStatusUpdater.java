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
    var player = turn.getCauser();
    if (turn instanceof NormalMovePawnTurn) {
      var gridSize = match.getBoard().getGridSize();
      var movePawnTurn = (NormalMovePawnTurn) turn;
      var destination = movePawnTurn.getDestination();

      var goals = playerGoals.getGoalCoordinatesForPlayer(player, gridSize);
      if (goals.contains(destination)) {
        return new MatchStatus(player, Status.VICTORY);
      }
    }
//    if (match.getMatchHistory().getSize() >= 5 && doesStalemateRuleApply(match)) {
//      return new MatchStatus(QuoridorPlayer.NULL, Status.STALEMATE);
//    }
    return match.getMatchStatus();
  }

  private boolean doesStalemateRuleApply(Match match) {
    var curr = match;
    var prev1 = curr.getMatchHistory().pop().getMatch();
    var prev2 = prev1.getMatchHistory().pop().getMatch();
    var prev3 = prev2.getMatchHistory().pop().getMatch();
    var prev4 = prev3.getMatchHistory().pop().getMatch();
    var prev5 = prev4.getMatchHistory().pop().getMatch();

    return curr.equals(prev2) && prev2.equals(prev4) && prev1.equals(prev3) && prev3.equals(prev5);
  }
}
