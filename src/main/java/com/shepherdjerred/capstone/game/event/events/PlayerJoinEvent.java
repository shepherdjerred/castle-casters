package com.shepherdjerred.capstone.game.event.events;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.events.Event;

public record PlayerJoinEvent(Player player) implements Event {
}
