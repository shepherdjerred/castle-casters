package com.shepherdjerred.capstone.game.network.discovery;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import lombok.Getter;

import java.net.SocketAddress;

/**
 * Represents the information a server sends to a client to identify a game.
 */
@Getter
public record ServerInformation(SocketAddress address, Lobby lobby) {

}
