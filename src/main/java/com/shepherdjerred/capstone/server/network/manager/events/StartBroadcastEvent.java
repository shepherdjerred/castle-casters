package com.shepherdjerred.capstone.server.network.manager.events;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.events.Event;
import lombok.Getter;

@Getter
public record StartBroadcastEvent(Lobby lobby) implements Event {
}
