package com.shepherdjerred.capstone.engine.audio;

import com.shepherdjerred.capstone.engine.resource.Resource;
import lombok.Getter;

import static org.lwjgl.openal.AL10.alDeleteBuffers;

@Getter
public record Audio(AudioName audioName, int alBufferName, int channels, int sampleRate) implements Resource {

  @Override
  public void cleanup() {
    alDeleteBuffers(alBufferName);
  }
}
