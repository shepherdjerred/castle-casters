package com.shepherdjerred.capstone.logic.turn;

import com.shepherdjerred.capstone.logic.board.Coordinate;

public interface JumpPawnTurn extends MovePawnTurn {

  Coordinate getPivot();
}
