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
public class HumanPlayer implements Player {

  private final UUID uuid;
  private final String name;
  private final Element element;
}
