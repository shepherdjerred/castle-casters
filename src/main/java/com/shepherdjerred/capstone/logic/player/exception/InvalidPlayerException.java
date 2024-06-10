package com.shepherdjerred.capstone.logic.player.exception;

import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;

public class InvalidPlayerException extends RuntimeException {
  public InvalidPlayerException(QuoridorPlayer quoridorPlayer) {
    super(quoridorPlayer.toString());
  }
}
