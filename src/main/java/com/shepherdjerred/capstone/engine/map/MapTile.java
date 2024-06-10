package com.shepherdjerred.capstone.engine.map;

import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import com.shepherdjerred.capstone.engine.graphics.texture.TextureSheetCoordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * A single tile a on a map.
 */
@Getter
@ToString
@AllArgsConstructor
public class MapTile {

  private final MapCoordinate position;
  private final TextureName textureName;
  private final TextureSheetCoordinates textureSheetCoordinates;
}
