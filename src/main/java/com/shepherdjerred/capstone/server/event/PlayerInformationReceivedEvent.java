package com.shepherdjerred.capstone.server.event;

import com.shepherdjerred.capstone.common.player.PlayerInformation;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.server.network.server.Connection;

public record PlayerInformationReceivedEvent(PlayerInformation playerInformation,
                                             Connection connection) implements Event {

}
