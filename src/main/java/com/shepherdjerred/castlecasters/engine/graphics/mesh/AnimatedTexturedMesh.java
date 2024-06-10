package com.shepherdjerred.castlecasters.engine.graphics.mesh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnimatedTexturedMesh {

  private final Map<Integer, TexturedMesh> frames = new HashMap<>();

  public void setFrame(Integer integer, TexturedMesh mesh) {
    frames.put(integer, mesh);
  }

  public Set<TexturedMesh> getMeshes() {
    return new HashSet<>(frames.values());
  }

  public void render(int frame) {
    var mesh = frames.get(frame);
    mesh.render();
  }
}
