package com.shepherdjerred.capstone.engine.graphics.mesh;

import com.shepherdjerred.capstone.engine.graphics.texture.Texture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TexturedMesh {

  private final Mesh mesh;
  private final Texture texture;

  public void render() {
    texture.bind();
    mesh.render();
  }
}
