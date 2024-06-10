package com.shepherdjerred.capstone.engine.events.audio;

import com.shepherdjerred.capstone.engine.audio.SourcedAudio;
import com.shepherdjerred.capstone.events.Event;

public record PlayAudioEvent(SourcedAudio audio) implements Event {

}
