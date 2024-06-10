package com.shepherdjerred.capstone.common.player;

import java.util.UUID;

public interface Player {

  UUID getUuid();

  String getName();

  Element getElement();
}
