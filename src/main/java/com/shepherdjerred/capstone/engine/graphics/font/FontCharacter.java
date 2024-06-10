package com.shepherdjerred.capstone.engine.graphics.font;

import com.shepherdjerred.capstone.engine.graphics.Quad;
import com.shepherdjerred.capstone.engine.graphics.texture.TextureQuad;
import lombok.Getter;

@Getter
public record FontCharacter(char character, float width, float height, Quad coordinates,
                            TextureQuad textureCoordinates) {

}
