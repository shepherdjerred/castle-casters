package com.shepherdjerred.capstone.server.network.manager.events;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class StartBroadcastEvent implements Event {
  private final Lobby lobby;
}
