package com.shepherdjerred.capstone.game.network.manager.event;

import com.shepherdjerred.capstone.common.GameState;
import com.shepherdjerred.capstone.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class StartServerEvent implements Event {
  private final GameState gameState;
}
