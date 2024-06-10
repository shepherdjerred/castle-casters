package com.shepherdjerred.capstone.game.network.discovery.event;

import com.shepherdjerred.capstone.game.network.discovery.ServerInformation;
import com.shepherdjerred.capstone.game.network.event.NetworkEvent;

public record ServerDiscoveredEvent(ServerInformation serverInformation) implements NetworkEvent {

}
