package com.shepherdjerred.capstone.game.event.events;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import com.shepherdjerred.capstone.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Tells the server who this player is.
 */
@Getter
@ToString
@AllArgsConstructor
public class IdentifyPlayerEvent implements Event {

  private final PlayerInformation playerInformation;
}
