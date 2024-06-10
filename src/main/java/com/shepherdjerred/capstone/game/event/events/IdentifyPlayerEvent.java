package com.shepherdjerred.capstone.game.event.events;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import com.shepherdjerred.capstone.events.Event;

/**
 * Tells the server who this player is.
 */
public record IdentifyPlayerEvent(PlayerInformation playerInformation) implements Event {

}
