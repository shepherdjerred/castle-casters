package com.shepherdjerred.capstone.ai.alphabeta.pruning.rules;

import com.shepherdjerred.capstone.logic.turn.Turn;

public interface TurnPruningRule {

  boolean shouldPrune(Turn turn);
}
