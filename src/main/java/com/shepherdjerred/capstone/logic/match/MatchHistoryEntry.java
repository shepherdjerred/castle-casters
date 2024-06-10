package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchHistoryEntry {

  /**
   * A Match state.
   */
  private final Match match;
  /**
   * A Turn applied to the Match state to create a new Match state.
   */
  private final Turn turn;
}
