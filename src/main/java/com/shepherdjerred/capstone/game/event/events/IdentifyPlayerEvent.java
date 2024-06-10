package com.shepherdjerred.capstone.game.event.events;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import com.shepherdjerred.capstone.events.Event;
import lombok.Getter;

/**
 * Tells the server who this player is.
 */
@Getter
public record IdentifyPlayerEvent(PlayerInformation playerInformation) implements Event {

}
