package com.shepherdjerred.capstone.server.network.event.events;

import com.shepherdjerred.capstone.server.network.server.Connection;

public record ClientDisconnectedEvent(Connection connection) implements NetworkEvent {

}
