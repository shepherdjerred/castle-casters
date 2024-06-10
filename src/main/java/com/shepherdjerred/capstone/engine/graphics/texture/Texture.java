package com.shepherdjerred.capstone.engine.graphics.texture;

import com.shepherdjerred.capstone.engine.resource.Resource;
import lombok.Getter;

import static org.lwjgl.opengl.GL11.*;

@Getter
public record Texture(TextureName textureName, int glTextureId, int width, int height,
                      int numberOfChannels) implements Resource {

  public void bind() {
    glBindTexture(GL_TEXTURE_2D, glTextureId);
  }

  @Override
  public void cleanup() {
    glDeleteTextures(glTextureId);
  }
}
