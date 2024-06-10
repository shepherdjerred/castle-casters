package com.shepherdjerred.capstone.engine.events.audio;

import com.shepherdjerred.capstone.engine.audio.SourcedAudio;
import com.shepherdjerred.capstone.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PlayAudioEvent implements Event {

  private final SourcedAudio audio;
}
