package com.shepherdjerred.capstone.common.chat;

import com.shepherdjerred.capstone.common.player.Player;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ChatMessage implements Comparable<ChatMessage> {

  private final Player sender;
  private final String message;
  private final Instant time;

  @Override
  public int compareTo(ChatMessage chatMessage) {
    return time.compareTo(chatMessage.getTime());
  }
}
