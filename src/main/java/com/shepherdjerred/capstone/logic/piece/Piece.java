package com.shepherdjerred.capstone.logic.piece;

import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;

public interface Piece {

  QuoridorPlayer owner();

  char toChar();
}
