package com.shepherdjerred.capstone.engine.map;

import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import com.shepherdjerred.capstone.engine.resource.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MapLayers implements Resource, Iterable<Layer> {

  private final MapDimensions dimension;
  private final SortedMap<Integer, Layer> layerMap;
  private final Set<TextureName> textureNames;

  public MapLayers(MapDimensions mapDimensions) {
    this.dimension = mapDimensions;
    this.textureNames = new HashSet<>();
    layerMap = new TreeMap<>();
  }

  public void setLayer(Integer i, Layer layer) {
    if (layerMap.containsKey(i)) {
      throw new IllegalArgumentException("Map already contains layer at this z");
    }
    layerMap.put(i, layer);
    textureNames.addAll(layer.getLayerTextures());
  }

  public Layer getLayer(Integer i) {
    return layerMap.get(i);
  }

  @Override
  public void cleanup() {

  }

  @Override
  public Iterator<Layer> iterator() {
    return layerMap.values().iterator();
  }
}
