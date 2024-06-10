package com.shepherdjerred.capstone.engine.graphics.mesh;

import com.shepherdjerred.capstone.engine.graphics.texture.Texture;

public record TexturedMesh(Mesh mesh, Texture texture) {

  public void render() {
    texture.bind();
    mesh.render();
  }
}
