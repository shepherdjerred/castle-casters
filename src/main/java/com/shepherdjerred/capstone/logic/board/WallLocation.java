package com.shepherdjerred.capstone.logic.board;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class WallLocation {

  private final Coordinate firstCoordinate;
  private final Coordinate vertex;
  private final Coordinate secondCoordinate;


  public WallLocation(Coordinate firstCoordinate,
      Coordinate vertex,
      Coordinate secondCoordinate) {
    Preconditions.checkNotNull(firstCoordinate);
    Preconditions.checkNotNull(vertex);
    Preconditions.checkNotNull(secondCoordinate);
    this.firstCoordinate = firstCoordinate;
    this.vertex = vertex;
    this.secondCoordinate = secondCoordinate;
    if (isInvalid()) {
      throw new IllegalArgumentException();
    }
  }

  private boolean isInvalid() {
    return !areCoordinatesAdjacentToVertex() || !areCoordinatesInStraightLine();
  }

  private boolean areCoordinatesAdjacentToVertex() {
    return firstCoordinate.isAdjacent(vertex) && secondCoordinate.isAdjacent(vertex);
  }

  private boolean areCoordinatesInStraightLine() {
    return (firstCoordinate.getX() == vertex.getX() && secondCoordinate.getX() == vertex.getX())
        || (firstCoordinate.getY() == vertex.getY() && secondCoordinate.getY() == vertex.getY());
  }
}
