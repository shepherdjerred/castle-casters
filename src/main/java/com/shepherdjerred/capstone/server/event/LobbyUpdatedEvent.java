package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.events.Event;

public record LobbyUpdatedEvent(Lobby newLobby) implements Event {
}
