package com.shepherdjerred.capstone.game.event.events;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.logic.turn.Turn;

public record DoTurnEvent(Turn turn) implements Event {
}
