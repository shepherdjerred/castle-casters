package com.shepherdjerred.capstone.common.player;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PlayerInformation {

  private final UUID uuid;
  private final String name;
}
