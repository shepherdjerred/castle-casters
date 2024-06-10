package com.shepherdjerred.capstone.engine.audio;

import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.ALC10.ALC_DEFAULT_DEVICE_SPECIFIER;
import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;
import static org.lwjgl.openal.ALC10.alcOpenDevice;

import com.shepherdjerred.capstone.engine.events.audio.PlayAudioEvent;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;

@Log4j2
@RequiredArgsConstructor
public class AudioPlayer {

  private SourcedAudio currentAudio = null;
  private final EventBus<Event> eventBus;
  private long device;
  private long context;

  public void initialize() {
    String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
    device = alcOpenDevice(defaultDeviceName);

    int[] attributes = {0};
    context = alcCreateContext(device, attributes);
    alcMakeContextCurrent(context);

    ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
    AL.createCapabilities(alcCapabilities);

    setupListener();
  }

  private void play(SourcedAudio sourcedAudio) {
    // TODO this could be better.
    if (currentAudio == null || sourcedAudio.getAudio().getAudioName() != currentAudio.getAudio()
        .getAudioName()) {
      currentAudio = sourcedAudio;
      log.info("Playing music");
      alSourcei(sourcedAudio.getAlSourceName(),
          AL_BUFFER,
          sourcedAudio.getAudio().getAlBufferName());
      alSourcePlay(sourcedAudio.getAlSourceName());
    } else {
      log.info("Skipping music since it's already playing.");
    }
  }

  private void setupListener() {
    eventBus.registerHandler(PlayAudioEvent.class,
        playAudioEvent -> play(playAudioEvent.getAudio()));
  }

  public void cleanup() {
    alcDestroyContext(context);
    alcCloseDevice(device);
  }
}
