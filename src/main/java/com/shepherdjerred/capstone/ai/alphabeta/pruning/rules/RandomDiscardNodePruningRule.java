package com.shepherdjerred.capstone.ai.alphabeta.pruning.rules;

import com.shepherdjerred.capstone.ai.alphabeta.pruning.PruningQuoridorNode;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import java.util.Random;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RandomDiscardNodePruningRule implements NodePruningRule {

  private static final int MIN = 0;
  private static final int MAX = 100;

  private final Random random = new Random();
  private final int chance;

  @Override
  public boolean shouldPrune(PruningQuoridorNode node) {
    if (!(node.getTurn() instanceof MovePawnTurn)) {
      return MIN + random.nextInt(MAX - MIN + 1) < chance;
    } else {
      return false;
    }
  }
}
