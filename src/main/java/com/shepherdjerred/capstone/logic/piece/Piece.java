package com.shepherdjerred.capstone.logic.piece;

import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;

public interface Piece {

  QuoridorPlayer getOwner();

  char toChar();
}
