package com.shepherdjerred.capstone.engine.audio;

import static org.lwjgl.openal.AL10.alDeleteBuffers;

import com.shepherdjerred.capstone.engine.resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Audio implements Resource {

  private final AudioName audioName;
  private final int alBufferName;
  private final int channels;
  private final int sampleRate;

  @Override
  public void cleanup() {
    alDeleteBuffers(alBufferName);
  }
}
