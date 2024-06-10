package com.shepherdjerred.capstone.game.network.manager.event;

import com.shepherdjerred.capstone.events.Event;
import java.net.SocketAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ConnectServerEvent implements Event {

  private final SocketAddress address;
}
