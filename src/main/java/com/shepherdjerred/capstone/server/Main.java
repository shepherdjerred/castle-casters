package com.shepherdjerred.capstone.server;

import com.shepherdjerred.capstone.common.Constants;
import com.shepherdjerred.capstone.common.GameState;
import com.shepherdjerred.capstone.common.chat.ChatHistory;
import com.shepherdjerred.capstone.common.lobby.Lobby;
import java.net.InetSocketAddress;

public class Main {

  public static void main(String[] args) {
    var gameState = new GameState(Lobby.fromDefaultLobbySettings("Test Lobby"),
        null,
        new ChatHistory());

    var server = new GameServer(gameState, new InetSocketAddress(Constants.GAME_PORT),
        new InetSocketAddress(Constants.DISCOVERY_PORT));
    server.run();
  }
}
