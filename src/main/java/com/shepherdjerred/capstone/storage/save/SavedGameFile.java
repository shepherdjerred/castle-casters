package com.shepherdjerred.capstone.storage.save;

import java.nio.file.Path;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class SavedGameFile implements SavedGame {

  private final String name;
  private final Instant time;
  private final Path path;

}
