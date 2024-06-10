package com.shepherdjerred.capstone.game.event.events;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.logic.turn.Turn;
import lombok.Getter;

@Getter
public record TryDoTurnEvent(Turn turn) implements Event {

}
