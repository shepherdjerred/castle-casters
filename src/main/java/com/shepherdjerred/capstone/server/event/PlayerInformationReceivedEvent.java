package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.server.network.server.Connection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PlayerInformationReceivedEvent implements Event {

  private final PlayerInformation playerInformation;
  private final Connection connection;
}
