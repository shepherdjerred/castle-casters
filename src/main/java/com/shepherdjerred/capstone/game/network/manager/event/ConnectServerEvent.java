package com.shepherdjerred.capstone.game.network.manager.event;

import com.shepherdjerred.capstone.events.Event;
import lombok.Getter;

import java.net.SocketAddress;

@Getter
public record ConnectServerEvent(SocketAddress address) implements Event {

}
