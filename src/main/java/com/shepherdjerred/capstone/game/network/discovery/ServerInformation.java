package com.shepherdjerred.capstone.game.network.discovery;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import java.net.SocketAddress;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents the information a server sends to a client to identify a game.
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ServerInformation {

  private final SocketAddress address;
  private final Lobby lobby;
}
