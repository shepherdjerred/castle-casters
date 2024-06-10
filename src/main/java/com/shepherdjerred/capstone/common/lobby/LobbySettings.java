package com.shepherdjerred.capstone.common.lobby;

import com.shepherdjerred.capstone.common.GameMap;
import com.shepherdjerred.capstone.logic.match.MatchSettings;

public record LobbySettings(String name, MatchSettings matchSettings,
                            com.shepherdjerred.capstone.common.lobby.LobbySettings.LobbyType lobbyType,
                            boolean isDuplicateElementsEnabled, GameMap gameMap) {

  public enum LobbyType {
    LOCAL, NETWORK
  }
}
