package com.shepherdjerred.capstone.engine;

import com.shepherdjerred.capstone.engine.window.WindowSize;

public interface GameLogic {

  void initialize(WindowSize windowSize) throws Exception;

  void updateGameState(float interval);

  void render();

  void cleanup();
}
