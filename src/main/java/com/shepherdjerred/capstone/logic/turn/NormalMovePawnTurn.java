package com.shepherdjerred.capstone.logic.turn;

import com.shepherdjerred.capstone.logic.board.Coordinate;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.Getter;

@Getter
public record NormalMovePawnTurn(QuoridorPlayer causer, Coordinate source,
                                 Coordinate destination) implements MovePawnTurn {

}
