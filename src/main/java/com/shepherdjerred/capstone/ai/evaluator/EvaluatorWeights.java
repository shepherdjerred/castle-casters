package com.shepherdjerred.capstone.ai.evaluator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class EvaluatorWeights {
  private final double adjacentPawnsWeight;
  private final double opponentsShortestPathWeight;
  private final double remainingWallsWeight;
  private final double shortestPathWeight;
  private final double wallsNearbyWeight;
}
