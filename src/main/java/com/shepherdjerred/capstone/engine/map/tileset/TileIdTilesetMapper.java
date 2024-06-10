package com.shepherdjerred.capstone.engine.map.tileset;

import java.util.SortedSet;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Finds what tileset a tile ID belongs to based on a set of tilesets.
 */
@Log4j2
@AllArgsConstructor
public class TileIdTilesetMapper {

  private final SortedSet<Tileset> tilesets;

  public Tileset getTilesetForTileId(int tileId) {
    Tileset lastMatch = tilesets.first();

    for (Tileset tileset : tilesets) {
      if (tileset.getFirstTile() > tileId) {
        break;
      } else {
        lastMatch = tileset;
      }
    }

//    log.info(String.format("%s matches %s", tileId, lastMatch));

    return lastMatch;
  }
}
