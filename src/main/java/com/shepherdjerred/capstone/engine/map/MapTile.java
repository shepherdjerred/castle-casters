package com.shepherdjerred.capstone.engine.map;

import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import com.shepherdjerred.capstone.engine.graphics.texture.TextureSheetCoordinates;

/**
 * A single tile on a map.
 */
public record MapTile(MapCoordinate position, TextureName textureName,
                      TextureSheetCoordinates textureSheetCoordinates) {

}
