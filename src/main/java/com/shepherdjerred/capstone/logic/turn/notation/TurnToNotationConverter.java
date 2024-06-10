package com.shepherdjerred.capstone.logic.turn.notation;

import com.google.common.base.Preconditions;
import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.board.WallLocation;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;

public class TurnToNotationConverter {

  public String convert(Turn turn) {
    if (turn instanceof MovePawnTurn) {
      return movePawnTurnToString((MovePawnTurn) turn);
    } else if (turn instanceof PlaceWallTurn) {
      return placeWallTurnToString((PlaceWallTurn) turn);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  private String movePawnTurnToString(MovePawnTurn turn) {
    var dest = turn.getDestination();
    int y = yCoordToNotationInt(dest.getY());
    char x = xCoordToNotationChar(dest.getX());
    return String.format("%s%s", x, y);
  }

  private String placeWallTurnToString(PlaceWallTurn turn) {
    var location = turn.getLocation();
    var space = getLowerLeftPawnSpace(location);
    var direction = getWallOrientation(location);
    int y = yCoordToNotationInt(space.getY());
    char x = xCoordToNotationChar(space.getX());
    var directionChar = direction.toNotationChar();
    return String.format("%s%s%s", x, y, directionChar);
  }

  private Coordinate getLowerLeftPawnSpace(WallLocation location) {
    return location.getVertex().toLeft().below();
  }

  private WallOrientation getWallOrientation(WallLocation location) {
    var c1 = location.getFirstCoordinate();
    var c2 = location.getSecondCoordinate();

    if (c1.getX() == c2.getX()) {
      return WallOrientation.VERTICAL;
    }

    if (c1.getY() == c2.getY()) {
      return WallOrientation.HORIZONTAL;
    }

    throw new UnsupportedOperationException();
  }

  private char xCoordToNotationChar(int xCoord) {
    Preconditions.checkArgument(xCoord % 2 == 0);
    Preconditions.checkArgument(xCoord <= 16);
    return (char) ((xCoord / 2) + 97);
  }

  private int yCoordToNotationInt(int yCoord) {
    Preconditions.checkArgument(yCoord % 2 == 0);
    Preconditions.checkArgument(yCoord <= 16);
    return (yCoord / 2) + 1;
  }
}
