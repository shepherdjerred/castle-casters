package com.shepherdjerred.capstone.engine.events.scene;

import com.shepherdjerred.capstone.engine.scene.Scene;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SceneTransitionEvent implements SceneEvent {

  private final Scene newScene;
}
