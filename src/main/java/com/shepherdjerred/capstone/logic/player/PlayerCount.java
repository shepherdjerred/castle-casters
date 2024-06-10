package com.shepherdjerred.capstone.logic.player;

public enum PlayerCount {
  TWO, FOUR;

  public int toInt() {
    switch (this) {
      case TWO:
        return 2;
      case FOUR:
        return 4;
      default:
        throw new UnsupportedOperationException(this.toString());
    }
  }
}
