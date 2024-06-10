package com.shepherdjerred.capstone.server.network.event.events;

import com.shepherdjerred.capstone.server.network.server.Connection;
import lombok.Getter;

@Getter
public record ClientDisconnectedEvent(Connection connection) implements NetworkEvent {

}
