package com.shepherdjerred.capstone.common.chat;

import com.shepherdjerred.capstone.common.player.Player;
import lombok.Getter;

import java.time.Instant;

@Getter
public record ChatMessage(Player sender, String message, Instant time) implements Comparable<ChatMessage> {

  @Override
  public int compareTo(ChatMessage chatMessage) {
    return time.compareTo(chatMessage.time());
  }
}
