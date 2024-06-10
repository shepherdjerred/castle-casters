package com.shepherdjerred.capstone.storage.save;

import lombok.Getter;

import java.nio.file.Path;
import java.time.Instant;

@Getter
public record SavedGameFile(String name, Instant time, Path path) implements SavedGame {

}
