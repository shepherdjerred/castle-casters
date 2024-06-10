package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.Getter;

/**
 * @param match A Match state.
 * @param turn  A Turn applied to the Match state to create a new Match state.
 */
@Getter
public record MatchHistoryEntry(Match match, Turn turn) {

}
