package com.shepherdjerred.capstone;

import com.shepherdjerred.capstone.engine.GameEngine;
import com.shepherdjerred.capstone.engine.window.WindowSettings;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.game.CastleCastersGame;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

  private static EventBus<Event> eventBus = new EventBus<>();

  public static void main(String[] args) {
    try {
      run();
    } catch (Exception e) {
      log.catching(e);
      log.error(e);
      System.exit(1);
    }
  }

  private static void run() throws Exception {
    var settings = getSettings();
    var logic = new CastleCastersGame(eventBus);
    var engine = new GameEngine(logic, settings, eventBus);
    engine.run();
  }

  private static WindowSettings getSettings() {
    return new WindowSettings("Castle Casters",
        new WindowSize(1360, 768),
        true,
        true);
  }
}
