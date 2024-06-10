package com.shepherdjerred.capstone.engine.map;

import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import com.shepherdjerred.capstone.engine.graphics.texture.TextureSheetCoordinates;
import lombok.Getter;

/**
 * A single tile on a map.
 */
@Getter
public record MapTile(MapCoordinate position, TextureName textureName,
                      TextureSheetCoordinates textureSheetCoordinates) {

}
