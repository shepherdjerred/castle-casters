package com.shepherdjerred.capstone.ai.alphabeta.pruning.rules;

import com.shepherdjerred.capstone.ai.alphabeta.pruning.PruningQuoridorNode;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@AllArgsConstructor
public class DeepWallNodePruningRule implements NodePruningRule {

  private final int maxWallDepth;

  @Override
  public boolean shouldPrune(PruningQuoridorNode node) {
    if (node.getTurn() instanceof PlaceWallTurn) {
      return node.getCurrentDepth() > maxWallDepth;
    }
    return false;
  }
}
