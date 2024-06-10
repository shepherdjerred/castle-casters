package com.shepherdjerred.capstone.game.objects.background.parallax;

import java.util.SortedMap;
import java.util.TreeMap;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ParallaxBackgroundData {

  private final int numberOfLayers;
  private final SortedMap<Integer, LayerData> layers;

  public ParallaxBackgroundData(int numberOfLayers) {
    this.numberOfLayers = numberOfLayers;
    layers = new TreeMap<>();
  }

  public LayerData getLayerData(int layer) {
    return layers.get(layer);
  }

  public void setLayerData(int layer, LayerData layerData) {
    layers.put(layer, layerData);
  }

}
