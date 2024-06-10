package com.shepherdjerred.capstone.server.network.event.events;

import com.shepherdjerred.capstone.server.network.server.Connection;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ClientDisconnectedEvent implements NetworkEvent {

  private final Connection connection;
}
