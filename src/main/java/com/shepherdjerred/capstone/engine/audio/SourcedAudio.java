package com.shepherdjerred.capstone.engine.audio;

import static org.lwjgl.openal.AL10.alDeleteSources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SourcedAudio {

  private final Audio audio;
  private final int alSourceName;

  public void cleanup() {
    alDeleteSources(alSourceName);
  }
}
