package com.shepherdjerred.capstone.common.player;

import java.util.UUID;

public record AiPlayer(UUID uuid, String name, Element element,
                       com.shepherdjerred.capstone.common.player.AiPlayer.Difficulty difficulty) implements Player {

  public enum Difficulty {
    HARD
  }
}
