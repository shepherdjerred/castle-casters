package com.shepherdjerred.capstone.storage.save;

import com.shepherdjerred.capstone.logic.match.Match;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface SavedGameRepository {

  Set<SavedGame> getSaves() throws IOException;

  Optional<Match> loadMatch(SavedGame savedGame) throws IOException;

  void saveMatch(String name, Match match) throws IOException;
}
