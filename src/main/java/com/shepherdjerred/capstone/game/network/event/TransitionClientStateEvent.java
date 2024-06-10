package com.shepherdjerred.capstone.game.network.event;

import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.game.network.client.state.NetworkClientState;
import lombok.Getter;

@Getter
public record TransitionClientStateEvent(NetworkClientState newState) implements Event {

}
