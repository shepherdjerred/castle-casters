package com.shepherdjerred.capstone.common.player;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class AiPlayer implements Player {

  private final UUID uuid;
  private final String name;
  private final Element element;
  private final Difficulty difficulty;

  public enum Difficulty {
    HARD
  }
}
