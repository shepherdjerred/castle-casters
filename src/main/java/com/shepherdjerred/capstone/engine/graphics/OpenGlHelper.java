package com.shepherdjerred.capstone.engine.graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_GEQUAL;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11C.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class OpenGlHelper {

  public static void unbind2dTexture() {
    glBindTexture(GL_TEXTURE_2D, 0);
  }

  public static void unbindBuffer() {
    glBindBuffer(GL_ARRAY_BUFFER, 0);
  }

  public static void unbindVertexArray() {
    glBindVertexArray(0);
  }

  public static void enableTransparency() {
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
  }

  public static void disableTransparency() {
    glDisable(GL_BLEND);
  }

  public static void enableDepthBuffer() {
    glClearDepth(-1f);
    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_GEQUAL);
    glDepthMask(true);
  }

  public static void disableDepthBuffer() {
    glDisable(GL_DEPTH_TEST);
    glDepthMask(false);
  }

  public static void setClearColor() {
    glClearColor(0, 0, 0, 0);
  }

  public static void setClearColor(Color color) {
    glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  public static void clearScreen() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  }

  public static void enableWireframe() {
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
  }

  public static void disableWireframe() {
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
  }
}
