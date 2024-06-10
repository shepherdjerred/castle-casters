package com.shepherdjerred.capstone.engine.graphics.texture;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;

import com.shepherdjerred.capstone.engine.resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Texture implements Resource {

  private final TextureName textureName;
  private final int glTextureId;
  private final int width;
  private final int height;
  private final int numberOfChannels;

  public void bind() {
    glBindTexture(GL_TEXTURE_2D, glTextureId);
  }

  @Override
  public void cleanup() {
    glDeleteTextures(glTextureId);
  }
}
