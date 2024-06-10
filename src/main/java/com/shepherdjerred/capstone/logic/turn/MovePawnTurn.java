package com.shepherdjerred.capstone.logic.turn;

import com.shepherdjerred.capstone.logic.board.Coordinate;

public interface MovePawnTurn extends Turn {

  Coordinate source();

  Coordinate destination();
}
