package com.shepherdjerred.capstone.engine.graphics.mesh;

import com.shepherdjerred.capstone.engine.graphics.texture.Texture;
import lombok.Getter;

@Getter
public record TexturedMesh(Mesh mesh, Texture texture) {

  public void render() {
    texture.bind();
    mesh.render();
  }
}
