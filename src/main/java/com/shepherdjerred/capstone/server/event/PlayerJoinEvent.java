package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.server.network.server.Connection;
import lombok.Getter;

@Getter
public record PlayerJoinEvent(Player player, Connection connection) implements Event {

}
