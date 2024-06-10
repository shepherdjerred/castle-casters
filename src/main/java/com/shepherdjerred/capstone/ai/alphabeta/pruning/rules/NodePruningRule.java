package com.shepherdjerred.capstone.ai.alphabeta.pruning.rules;

import com.shepherdjerred.capstone.ai.alphabeta.pruning.PruningQuoridorNode;

public interface NodePruningRule {

  boolean shouldPrune(PruningQuoridorNode node);
}
