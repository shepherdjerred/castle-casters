package com.shepherdjerred.capstone.engine.audio;

import lombok.Getter;

import static org.lwjgl.openal.AL10.alDeleteSources;

@Getter
public record SourcedAudio(Audio audio, int alSourceName) {

  public void cleanup() {
    alDeleteSources(alSourceName);
  }
}
