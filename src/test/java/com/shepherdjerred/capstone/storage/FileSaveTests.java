package com.shepherdjerred.capstone.storage;

import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchSettings;
import com.shepherdjerred.capstone.logic.match.serialization.MatchJsonSerializer;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.storage.save.FilesystemSavedGameRepository;
import com.shepherdjerred.capstone.storage.save.SavedGameFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

public class FileSaveTests {

  private Match match = Match.from(new MatchSettings(10, QuoridorPlayer.ONE, PlayerCount.TWO),
      new BoardSettings(9, PlayerCount.TWO));
  private Path currentRelativePath = Paths.get("").toAbsolutePath();
  private FilesystemSavedGameRepository repository = new FilesystemSavedGameRepository(currentRelativePath);

  @Test
  public void testSaves() throws IOException {
    String fileName = "My match.match";

    repository.saveMatch(fileName, match);

    var savedGame = new SavedGameFile(fileName, Instant.now(), currentRelativePath);
    var loadedGame = repository.loadMatch(savedGame);

    Assertions.assertTrue(loadedGame.isPresent());
    Assertions.assertTrue(compareMatch(match, loadedGame.get()));

    Files.deleteIfExists(Paths.get(fileName));
  }

  @Test
  public void getAllSaves() throws IOException {
    repository.saveMatch("Match1.match", match);
    repository.saveMatch("Match2.match", match);
    repository.saveMatch("Match3.match", match);

    var savedGames = repository.getSaves();

    Assertions.assertEquals(3, savedGames.size());

    Files.deleteIfExists(Paths.get("Match1.match"));
    Files.deleteIfExists(Paths.get("Match2.match"));
    Files.deleteIfExists(Paths.get("Match3.match"));
  }

  @Test
  public void deleteSave() throws IOException {
    repository.saveMatch("Match1.match", match);
    repository.saveMatch("Match2.match", match);
    repository.saveMatch("Match3.match", match);

    var savedGames = repository.getSaves();

    Assertions.assertEquals(3, savedGames.size());

    repository.deleteSave("Match1.match");
    repository.deleteSave("Match2.match");
    repository.deleteSave("Match3.match");

    savedGames = repository.getSaves();

    Assertions.assertEquals(0, savedGames.size());
  }

  private boolean compareMatch(Match match1, Match match2) {
    String match1String = new MatchJsonSerializer().toJsonString(match1);
    String match2String = new MatchJsonSerializer().toJsonString(match2);

    return match1String.equals(match2String);
  }
}
