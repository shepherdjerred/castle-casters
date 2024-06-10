package com.shepherdjerred.capstone.common.player;

import lombok.Getter;

import java.util.UUID;

@Getter
public record PlayerInformation(UUID uuid, String name) {

}
