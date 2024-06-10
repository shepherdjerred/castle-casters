package com.shepherdjerred.capstone.engine.map;

import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Layer implements Comparable<Layer>, Iterable<MapTile> {

  private final MapDimensions dimension;
  @Getter
  private final Map<MapCoordinate, MapTile> tiles;
  @Getter
  private final int z;
  @Getter
  private final Set<TextureName> layerTextures;

  public Layer(MapDimensions mapDimensions, int z) {
    this.dimension = mapDimensions;
    this.tiles = new HashMap<>();
    this.layerTextures = new HashSet<>();
    this.z = z;
  }

  public void setTile(MapCoordinate coordinate, MapTile mapTile) {
    if (!dimension.contains(coordinate)) {
      throw new IllegalArgumentException(coordinate + " is not a coordinate in this layer");
    }
    if (tiles.containsKey(coordinate)) {
      throw new IllegalArgumentException(coordinate + " is already contained in this map.");
    }
    tiles.put(coordinate, mapTile);
    layerTextures.add(mapTile.getTextureName());
  }

  public Optional<MapTile> getTile(MapCoordinate coordinate) {
    if (tiles.containsKey(coordinate)) {
      return Optional.of(tiles.get(coordinate));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public int compareTo(Layer layer) {
    return z - layer.getZ();
  }

  @Override
  public Iterator<MapTile> iterator() {
    return tiles.values().iterator();
  }

}
