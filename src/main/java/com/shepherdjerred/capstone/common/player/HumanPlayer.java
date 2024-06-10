package com.shepherdjerred.capstone.common.player;

import lombok.Getter;

import java.util.UUID;

@Getter
public record HumanPlayer(UUID uuid, String name, Element element) implements Player {

}
