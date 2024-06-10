package com.shepherdjerred.capstone.logic.piece;

import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.ToString;

/**
 * Represents an empty piece
 */
@ToString
public enum NullPiece implements Piece {
  INSTANCE;

  @Override
  public QuoridorPlayer getOwner() {
    return QuoridorPlayer.NULL;
  }

  @Override
  public char toChar() {
    return ' ';
  }
}
