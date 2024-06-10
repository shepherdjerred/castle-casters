package com.shepherdjerred.capstone.ai.evaluator;

public record EvaluatorWeights(double adjacentPawnsWeight, double opponentsShortestPathWeight,
                               double remainingWallsWeight, double shortestPathWeight, double wallsNearbyWeight) {
}
