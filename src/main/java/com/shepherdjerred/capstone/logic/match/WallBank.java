package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class WallBank {

  public final Map<QuoridorPlayer, Integer> playerWalls;

  public static WallBank from(PlayerCount playerCount, int numberOfWalls) {
    Map<QuoridorPlayer, Integer> walls = new HashMap<>();
    Set<QuoridorPlayer> quoridorPlayers = new HashSet<>();
    quoridorPlayers.add(QuoridorPlayer.ONE);
    quoridorPlayers.add(QuoridorPlayer.TWO);
    if (playerCount == PlayerCount.FOUR) {
      quoridorPlayers.add(QuoridorPlayer.THREE);
      quoridorPlayers.add(QuoridorPlayer.FOUR);
    }
    quoridorPlayers.forEach(player -> walls.put(player, numberOfWalls));
    return new WallBank(walls);
  }

  private WallBank(Map<QuoridorPlayer, Integer> playerWalls) {
    this.playerWalls = playerWalls;
  }

  public int getWallsLeft(QuoridorPlayer quoridorPlayer) {
    if (playerWalls.containsKey(quoridorPlayer)) {
      return playerWalls.get(quoridorPlayer);
    } else {
      throw new IllegalArgumentException(quoridorPlayer.toString());
    }
  }

  public WallBank takeWall(QuoridorPlayer quoridorPlayer) {
    if (playerWalls.containsKey(quoridorPlayer)) {
      Map<QuoridorPlayer, Integer> newWallCounts = new HashMap<>(playerWalls);
      var currentWallsLeft = getWallsLeft(quoridorPlayer) - 1;
      newWallCounts.put(quoridorPlayer, currentWallsLeft);
      return new WallBank(newWallCounts);
    } else {
      throw new IllegalArgumentException(quoridorPlayer.toString());
    }
  }
}
