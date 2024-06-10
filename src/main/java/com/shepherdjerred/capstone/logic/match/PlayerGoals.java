package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PlayerGoals {

  public Set<Coordinate> getGoalCoordinatesForPlayer(QuoridorPlayer quoridorPlayer, int gridSize) {
    Set<Coordinate> goals = new HashSet<>();
    if (quoridorPlayer == QuoridorPlayer.ONE) {
      for (int x = 0; x <= gridSize - 1; x += 2) {
        goals.add(new Coordinate(x, 16));
      }
    } else if (quoridorPlayer == QuoridorPlayer.TWO) {
      for (int x = 0; x <= gridSize - 1; x += 2) {
        goals.add(new Coordinate(x, 0));
      }
    } else if (quoridorPlayer == QuoridorPlayer.THREE) {
      for (int y = 0; y <= gridSize - 1; y += 2) {
        goals.add(new Coordinate(gridSize - 1, y));
      }
    } else if (quoridorPlayer == QuoridorPlayer.FOUR) {
      for (int y = 0; y <= gridSize - 1; y += 2) {
        goals.add(new Coordinate(0, y));
      }
    } else {
      throw new UnsupportedOperationException();
    }
    return goals;
  }
}
