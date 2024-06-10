package com.shepherdjerred.capstone.engine.graphics.font;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RED;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.stb.STBTruetype.stbtt_BakeFontBitmap;
import static org.lwjgl.stb.STBTruetype.stbtt_GetFontVMetrics;
import static org.lwjgl.stb.STBTruetype.stbtt_InitFont;

import com.shepherdjerred.capstone.engine.resource.ByteBufferLoader;
import com.shepherdjerred.capstone.engine.resource.ResourceFileLocator;
import com.shepherdjerred.capstone.engine.resource.ResourceLoader;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.system.MemoryStack;

@Log4j2
@AllArgsConstructor
public class FontLoader implements ResourceLoader<FontName, Font> {

  private final ResourceFileLocator fileLocator;

  public Font get(FontName fontName) throws Exception {
    var loader = new ByteBufferLoader();
    var filePath = fileLocator.getFontPath(fontName);
    var fontBuffer = loader.load(filePath);

    var info = STBTTFontinfo.create();
    if (!stbtt_InitFont(info, fontBuffer)) {
      throw new Exception("Unable to create font");
    }

    int ascent;
    int descent;
    int gap;

    try (var stack = MemoryStack.stackPush()) {
      var ascentBuffer = stack.mallocInt(1);
      var descentBuffer = stack.mallocInt(1);
      var gapBuffer = stack.mallocInt(1);

      stbtt_GetFontVMetrics(info, ascentBuffer, descentBuffer, gapBuffer);

      ascent = ascentBuffer.get(0);
      descent = descentBuffer.get(0);
      gap = gapBuffer.get(0);
    }

    var bitmapWidth = Math.round(512);
    var bitmapHeight = Math.round(512);
    var characters = STBTTBakedChar.malloc(96);
    var bitmapBuffer = BufferUtils.createByteBuffer(bitmapWidth * bitmapHeight);

    var value = stbtt_BakeFontBitmap(fontBuffer,
        24,
        bitmapBuffer,
        bitmapWidth,
        bitmapHeight,
        32,
        characters);

    if (value == 0) {
      throw new Exception("No characters fit into texture");
    }

    var glTextureName = glGenTextures();
    glBindTexture(GL_TEXTURE_2D, glTextureName);
    glTexImage2D(GL_TEXTURE_2D,
        0,
        GL_RED,
        bitmapWidth,
        bitmapHeight,
        0,
        GL_RED,
        GL_UNSIGNED_BYTE,
        bitmapBuffer);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    return new Font(fontName,
        ascent,
        descent,
        gap,
        glTextureName,
        bitmapWidth,
        bitmapHeight,
        characters);
  }
}
