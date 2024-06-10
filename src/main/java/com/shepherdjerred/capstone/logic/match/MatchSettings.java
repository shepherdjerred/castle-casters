package com.shepherdjerred.capstone.logic.match;

import com.google.common.base.Preconditions;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;

public record MatchSettings(int wallsPerPlayer, QuoridorPlayer startingQuoridorPlayer, PlayerCount playerCount) {

  public MatchSettings {
    Preconditions.checkArgument(startingQuoridorPlayer.toInt() <= playerCount.toInt());
  }


}
