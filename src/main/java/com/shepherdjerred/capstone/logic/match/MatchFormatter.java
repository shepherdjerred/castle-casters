package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.board.BoardFormatter;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.List;

/**
 * Utility class for formatting a match state as a string
 */
public class MatchFormatter {

  public String matchesToString(List<Match> states) {
    var sb = new StringBuilder();
    states.forEach(state -> sb.append(matchToString(state)).append("\n\n"));
    return sb.toString();
  }

  public String matchToString(Match match) {
    var activePlayer = match.getActivePlayerId();
    var board = new BoardFormatter().boardToString(match.getBoard());
    var turn = match.getMatchHistory().getSize() + 1;
    var walls = getPlayerWallsString(match);
    var victor = match.getMatchStatus().getVictor();
    return String.format("%sTurn #%d\nActive Player: %s\n\nWalls Remaining\n%s\nVictor: %s\n",
        board,
        turn,
        activePlayer,
        walls,
        victor);
  }

  private String getPlayerWallsString(Match match) {
    var players = match.getMatchSettings().getPlayerCount().toInt();
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= players; i++) {
      var player = QuoridorPlayer.fromInt(i);
      var walls = match.getWallsLeft(player);
      sb.append(String.format("Player %s: %d\n", player, walls));
    }
    return sb.toString();
  }

}
