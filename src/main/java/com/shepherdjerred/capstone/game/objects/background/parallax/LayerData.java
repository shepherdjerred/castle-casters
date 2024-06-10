package com.shepherdjerred.capstone.game.objects.background.parallax;

import com.shepherdjerred.capstone.engine.graphics.texture.TextureName;
import lombok.Getter;

@Getter
public record LayerData(TextureName textureName, boolean isStatic) {

}
