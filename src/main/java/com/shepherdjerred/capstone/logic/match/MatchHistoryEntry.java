package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.turn.Turn;

/**
 * @param match A Match state.
 * @param turn  A Turn applied to the Match state to create a new Match state.
 */
public record MatchHistoryEntry(Match match, Turn turn) {

}
