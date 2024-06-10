package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LobbyUpdatedEvent implements Event {
  private final Lobby newLobby;
}
