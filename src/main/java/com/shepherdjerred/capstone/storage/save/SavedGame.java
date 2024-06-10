package com.shepherdjerred.capstone.storage.save;

import java.time.Instant;

public interface SavedGame {

  String getName();

  Instant getTime();
}
