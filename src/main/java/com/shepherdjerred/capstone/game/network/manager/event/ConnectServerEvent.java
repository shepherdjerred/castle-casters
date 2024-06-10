package com.shepherdjerred.capstone.game.network.manager.event;

import com.shepherdjerred.capstone.events.Event;

import java.net.SocketAddress;

public record ConnectServerEvent(SocketAddress address) implements Event {

}
