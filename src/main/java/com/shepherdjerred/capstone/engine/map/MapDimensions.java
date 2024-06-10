package com.shepherdjerred.capstone.engine.map;

import lombok.Getter;

/**
 * The dimensions of a map.
 */
@Getter
public record MapDimensions(int width, int height) {

  public boolean contains(MapCoordinate mapCoordinate) {
    return mapCoordinate.x() < width && mapCoordinate.y() < height;
  }
}
