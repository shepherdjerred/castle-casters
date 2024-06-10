package com.shepherdjerred.capstone.logic.board.exception;

import com.shepherdjerred.capstone.logic.board.Coordinate;

public class CoordinateOutOfBoundsException extends RuntimeException {

  public CoordinateOutOfBoundsException(Coordinate coordinate) {
    super(coordinate.toString());
  }
}
