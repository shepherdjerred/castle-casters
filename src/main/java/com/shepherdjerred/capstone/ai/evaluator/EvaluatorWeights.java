package com.shepherdjerred.capstone.ai.evaluator;

import lombok.Getter;

@Getter
public record EvaluatorWeights(double adjacentPawnsWeight, double opponentsShortestPathWeight,
                               double remainingWallsWeight, double shortestPathWeight, double wallsNearbyWeight) {
}
