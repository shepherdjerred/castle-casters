package com.shepherdjerred.capstone.engine.graphics.texture;

import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnimatedTextureSheet {

  private final Texture texture;
  private final Map<Integer, TextureSheetCoordinates> frames;


}
