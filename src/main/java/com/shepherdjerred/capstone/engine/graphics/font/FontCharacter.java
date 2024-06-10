package com.shepherdjerred.capstone.engine.graphics.font;

import com.shepherdjerred.capstone.engine.graphics.Quad;
import com.shepherdjerred.capstone.engine.graphics.texture.TextureQuad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class FontCharacter {

  private final char character;
  private final float width;
  private final float height;
  private final Quad coordinates;
  private final TextureQuad textureCoordinates;

}
