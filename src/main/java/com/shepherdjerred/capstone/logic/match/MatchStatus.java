package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;

public record MatchStatus(QuoridorPlayer victor, com.shepherdjerred.capstone.logic.match.MatchStatus.Status status) {

  public enum Status {
    IN_PROGRESS, VICTORY, STALEMATE
  }
}
