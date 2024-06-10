package com.shepherdjerred.capstone.logic.match;

import com.google.common.base.Preconditions;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MatchSettings {

  private final int wallsPerPlayer;
  private final QuoridorPlayer startingQuoridorPlayer;
  private final PlayerCount playerCount;

  public MatchSettings(int wallsPerPlayer,
      QuoridorPlayer startingQuoridorPlayer,
      PlayerCount playerCount) {
    Preconditions.checkArgument(startingQuoridorPlayer.toInt() <= playerCount.toInt());
    this.wallsPerPlayer = wallsPerPlayer;
    this.startingQuoridorPlayer = startingQuoridorPlayer;
    this.playerCount = playerCount;
  }


}
