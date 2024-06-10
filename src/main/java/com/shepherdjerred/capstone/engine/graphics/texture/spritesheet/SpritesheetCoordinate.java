package com.shepherdjerred.capstone.engine.graphics.texture.spritesheet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents the position of a sprite in a spritesheet.
 */
@Getter
@ToString
@AllArgsConstructor
public class SpritesheetCoordinate {

  private final int x;
  private final int y;
}
