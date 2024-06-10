package com.shepherdjerred.capstone.engine.game.scenes.mainmenu;

import static com.shepherdjerred.capstone.engine.engine.audio.AudioName.THEME_MUSIC;
import static org.lwjgl.openal.AL10.alGenSources;

import com.shepherdjerred.capstone.engine.engine.audio.AudioName;
import com.shepherdjerred.capstone.engine.engine.audio.SourcedAudio;
import com.shepherdjerred.capstone.engine.engine.events.audio.PlayAudioEvent;
import com.shepherdjerred.capstone.engine.engine.events.scene.SceneActiveEvent;
import com.shepherdjerred.capstone.engine.engine.resource.ResourceManager;
import com.shepherdjerred.capstone.engine.engine.scene.SceneAudio;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MainMenuAudio implements SceneAudio {

  private final EventBus<Event> eventBus;
  private final ResourceManager resourceManager;
  private final Map<AudioName, SourcedAudio> audio;
  private final EventHandlerFrame<Event> handlerFrame;

  public MainMenuAudio(EventBus<Event> eventBus, ResourceManager resourceManager) {
    this.eventBus = eventBus;
    this.resourceManager = resourceManager;
    this.audio = new HashMap<>();
    this.handlerFrame = new EventHandlerFrame<>();
  }

  public void initialize() throws Exception {
    int sourcePointer = alGenSources();
    var sa = new SourcedAudio(resourceManager.get(THEME_MUSIC), sourcePointer);
    audio.put(THEME_MUSIC, sa);
    initializeHandlerFrame();
    eventBus.registerHandlerFrame(handlerFrame);
  }

  private void initializeHandlerFrame() {
    handlerFrame.registerHandler(SceneActiveEvent.class,
        sceneActiveEvent -> eventBus.dispatch(new PlayAudioEvent(audio.get(THEME_MUSIC))));
  }

  public void cleanup() {
    resourceManager.free(THEME_MUSIC);
    eventBus.removeHandlerFrame(handlerFrame);
  }
}
