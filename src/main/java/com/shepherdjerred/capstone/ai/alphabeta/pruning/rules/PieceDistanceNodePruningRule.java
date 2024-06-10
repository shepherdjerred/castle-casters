package com.shepherdjerred.capstone.ai.alphabeta.pruning.rules;

import com.shepherdjerred.capstone.ai.alphabeta.pruning.PruningQuoridorNode;
import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@AllArgsConstructor
public class PieceDistanceNodePruningRule implements NodePruningRule {

  private final int distance;

  @Override
  public boolean shouldPrune(PruningQuoridorNode node) {
    var match = node.getMatch();
    var turn = node.getTurn();
    var pieceCoords = match.getBoard().getPieceLocations();

    Set<Coordinate> turnCoords = new HashSet<>();
    if (turn instanceof MovePawnTurn) {
      return false;
    } else if (turn instanceof PlaceWallTurn) {
      var wallLocation = ((PlaceWallTurn) turn).getLocation();
      turnCoords.add(wallLocation.getFirstCoordinate());
      turnCoords.add(wallLocation.getVertex());
      turnCoords.add(wallLocation.getSecondCoordinate());
    } else {
      throw new UnsupportedOperationException();
    }


    var shouldBePruned = pieceCoords.stream()
        .filter(pieceCoord -> !turnCoords.contains(pieceCoord))
        .allMatch(pieceCoord -> turnCoords.stream()
            .allMatch(turnCoordinate -> Coordinate.calculateManhattanDistance(pieceCoord, turnCoordinate)
                > distance));

    if (shouldBePruned) {
      return true;
    } else {
      return false;
    }

  }
}
