package com.shepherdjerred.capstone.logic.player;

public enum QuoridorPlayer {
  ONE, TWO, THREE, FOUR, NULL;

  public int toInt() {
    return this.ordinal() + 1;
  }

  public static QuoridorPlayer fromInt(int i) {
    return QuoridorPlayer.values()[i - 1];
  }
}
