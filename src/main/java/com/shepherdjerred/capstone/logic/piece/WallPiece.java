package com.shepherdjerred.capstone.logic.piece;

import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.Getter;

@Getter
public record WallPiece(QuoridorPlayer owner) implements Piece {

  @Override
  public char toChar() {
    return 'W';
  }
}
