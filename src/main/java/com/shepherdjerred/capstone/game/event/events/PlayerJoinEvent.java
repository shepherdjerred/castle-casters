package com.shepherdjerred.capstone.game.event.events;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.events.Event;
import lombok.Getter;

@Getter
public record PlayerJoinEvent(Player player) implements Event {
}
