package com.shepherdjerred.capstone.logic.turn;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;

public record JumpPawnStraightTurn(QuoridorPlayer causer, Coordinate source, Coordinate destination,
                                   Coordinate pivot) implements JumpPawnTurn {

}
