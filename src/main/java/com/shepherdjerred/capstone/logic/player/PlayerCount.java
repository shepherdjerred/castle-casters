package com.shepherdjerred.capstone.logic.player;

public enum PlayerCount {
  TWO, FOUR;

  public int toInt() {
    return switch (this) {
      case TWO -> 2;
      case FOUR -> 4;
      default -> throw new UnsupportedOperationException(this.toString());
    };
  }
}
