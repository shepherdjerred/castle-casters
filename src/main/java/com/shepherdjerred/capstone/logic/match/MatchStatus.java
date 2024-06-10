package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MatchStatus {

  private final QuoridorPlayer victor;
  private final Status status;

  public enum Status {
    IN_PROGRESS, VICTORY, STALEMATE
  }
}
