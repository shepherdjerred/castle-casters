package com.shepherdjerred.capstone.storage.save;

import java.time.Instant;

public interface SavedGame {

  String name();

  Instant time();
}
