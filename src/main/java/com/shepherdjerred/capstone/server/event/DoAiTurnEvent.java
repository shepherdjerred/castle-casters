package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.events.Event;

public record DoAiTurnEvent(Player player) implements Event {

}
