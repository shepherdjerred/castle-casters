package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.logic.turn.Turn;

public record DoTurnEvent(Turn turn) implements Event {

}
