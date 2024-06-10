package com.shepherdjerred.capstone.game.network.manager.event;

import com.shepherdjerred.capstone.common.GameState;
import com.shepherdjerred.capstone.events.Event;

public record StartServerEvent(GameState gameState) implements Event {
}
