package com.shepherdjerred.capstone.common;

import com.shepherdjerred.capstone.common.chat.ChatHistory;
import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.logic.match.Match;
import lombok.Getter;

@Getter
public record GameState(Lobby lobby, Match match, ChatHistory chatHistory) {

  public GameState setLobby(Lobby lobby) {
    return new GameState(lobby, match, chatHistory);
  }

  public GameState setMatch(Match match) {
    return new GameState(lobby, match, chatHistory);
  }

  public GameState setChatHistory(ChatHistory chatHistory) {
    return new GameState(lobby, match, chatHistory);
  }
}
