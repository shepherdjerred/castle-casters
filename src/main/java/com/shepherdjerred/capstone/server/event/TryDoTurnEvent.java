package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.Getter;

@Getter
public record TryDoTurnEvent(Turn turn) implements Event {

}
