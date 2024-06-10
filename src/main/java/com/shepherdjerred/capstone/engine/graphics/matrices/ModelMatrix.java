package com.shepherdjerred.capstone.engine.graphics.matrices;

import com.shepherdjerred.capstone.engine.graphics.RendererCoordinate;
import lombok.Getter;
import org.joml.Matrix4f;

@Getter
public record ModelMatrix(RendererCoordinate translation, float rotation, float scale) {

  // TODO rotation should be done around the center of the object, not the origin
  // https://learnopengl.com/In-Practice/2D-Game/Rendering-Sprites
  // https://learnopengl.com/Getting-started/Transformations
  public Matrix4f getMatrix() {
    return new Matrix4f()
        .translate(translation.getX(), translation.getY(), translation.getZ())
        .rotate((float) Math.toRadians(rotation), 0, 0, 1)
        .scale(scale, scale, 1);
  }
}
