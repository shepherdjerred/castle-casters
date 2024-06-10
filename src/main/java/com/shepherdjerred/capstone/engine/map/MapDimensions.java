package com.shepherdjerred.capstone.engine.map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The dimensions of a map.
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MapDimensions {

  private final int width;
  private final int height;

  public boolean contains(MapCoordinate mapCoordinate) {
    return mapCoordinate.getX() < width && mapCoordinate.getY() < height;
  }
}
