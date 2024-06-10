package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ActivePlayerTracker {

  @Getter
  private final QuoridorPlayer activePlayer;
  private final PlayerCount playerCount;

  public QuoridorPlayer getNextActivePlayerId() {
    return getNextActivePlayerTracker().getActivePlayer();
  }

  public ActivePlayerTracker getNextActivePlayerTracker() {
    var activePlayerInt = activePlayer.toInt() + 1;
    var playerCountInt = playerCount.toInt();

    QuoridorPlayer nextPlayer;
    if (activePlayerInt > playerCountInt) {
      nextPlayer = QuoridorPlayer.ONE;
    } else {
      nextPlayer = QuoridorPlayer.fromInt(activePlayerInt);
    }

    return new ActivePlayerTracker(nextPlayer, playerCount);
  }

  public Set<QuoridorPlayer> getInactivePlayers() {
    Set<QuoridorPlayer> inactivePlayers = new HashSet<>();

    var activePlayerInt = activePlayer.toInt();
    var currentPlayerCount = playerCount.toInt();

    do{
      if (currentPlayerCount != activePlayerInt){
        inactivePlayers.add(QuoridorPlayer.fromInt(currentPlayerCount));
      }
      currentPlayerCount--;
    }while(currentPlayerCount > 0);

    return inactivePlayers;
  }
}
