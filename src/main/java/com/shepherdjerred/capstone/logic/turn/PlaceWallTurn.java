package com.shepherdjerred.capstone.logic.turn;

import com.shepherdjerred.capstone.logic.board.WallLocation;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.Getter;

@Getter
public record PlaceWallTurn(QuoridorPlayer causer, WallLocation location) implements Turn {

}
