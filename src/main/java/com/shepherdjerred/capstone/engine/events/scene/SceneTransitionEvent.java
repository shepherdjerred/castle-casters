package com.shepherdjerred.capstone.engine.events.scene;

import com.shepherdjerred.capstone.engine.scene.Scene;
import lombok.Getter;

@Getter
public record SceneTransitionEvent(Scene newScene) implements SceneEvent {

}
