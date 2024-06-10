package com.shepherdjerred.capstone.engine.graphics.matrices;

import com.shepherdjerred.capstone.engine.window.WindowSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.joml.Matrix4f;

@Getter
@ToString
@AllArgsConstructor
public class ProjectionMatrix {

  private final WindowSize windowSize;

  // https://gamedev.stackexchange.com/questions/59161/is-opengl-appropriate-for-2d-games
  // https://en.wikibooks.org/wiki/OpenGL_Programming/Modern_OpenGL_Tutorial_2D
  public Matrix4f getMatrix() {
    return new Matrix4f().ortho(0, windowSize.getWidth(), windowSize.getHeight(), 0, 1000, -1000);
  }
}
