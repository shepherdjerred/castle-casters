package com.shepherdjerred.capstone.engine.resource;

import java.io.IOException;
import java.nio.ByteBuffer;
import lombok.extern.log4j.Log4j2;
import org.lwjgl.BufferUtils;

@Log4j2
public class ByteBufferLoader {

  public ByteBuffer load(String filePath) throws IOException {
    log.trace("Loading file from jar: " + filePath);

    try (var stream = getClass().getResourceAsStream(filePath)) {
      if (stream == null) {
        throw new IllegalStateException("File doesn't exist: " + filePath);
      }
      var bytes = stream.readAllBytes();
      var buffer = BufferUtils.createByteBuffer(bytes.length);

      log.trace("File size: " + bytes.length / 1024 + "KB");

      buffer.put(bytes);
      buffer.flip();

      return buffer;
    }
  }
}