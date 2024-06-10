package com.shepherdjerred.capstone.game.network.discovery.event;

import com.shepherdjerred.capstone.game.network.discovery.ServerInformation;
import com.shepherdjerred.capstone.game.network.event.NetworkEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ServerDiscoveredEvent implements NetworkEvent {

  private final ServerInformation serverInformation;
}
