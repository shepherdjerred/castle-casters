package com.shepherdjerred.capstone.logic.board.search;

import com.github.bentorfs.ai.search.asearch.AStarSearchNode;
import com.shepherdjerred.capstone.logic.board.QuoridorBoard;
import com.shepherdjerred.capstone.logic.board.Coordinate;
import java.util.Set;

public interface BoardSearch {

  boolean hasPathToDestination(QuoridorBoard board, Coordinate source, Coordinate destination);

  boolean hasPathToAnyDestination(QuoridorBoard board, Coordinate source, Set<Coordinate> destinations);

  int getShortestPathToDestination(QuoridorBoard board, Coordinate source, Coordinate destination);

  int getShortestPathToAnyDestination(QuoridorBoard board, Coordinate source, Set<Coordinate> destinations);

  AStarSearchNode getPathToDestination(QuoridorBoard board, Coordinate source, Coordinate destination);

  AStarSearchNode getPathToAnyDestination(QuoridorBoard board, Coordinate source, Set<Coordinate> destination);
}
