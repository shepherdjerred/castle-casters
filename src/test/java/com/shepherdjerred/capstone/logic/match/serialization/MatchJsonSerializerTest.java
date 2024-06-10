package com.shepherdjerred.capstone.logic.match.serialization;

import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchSettings;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchJsonSerializerTest {

  @Test
  public void test() {
    var originalMatch = Match.from(new MatchSettings(10, QuoridorPlayer.ONE, PlayerCount.TWO),
        new BoardSettings(9, PlayerCount.TWO));

    var serializer = new MatchJsonSerializer();

    var bytes = serializer.toBytes(originalMatch);
    var deserializedMatch = serializer.fromBytes(bytes);

    Assertions.assertEquals(originalMatch, deserializedMatch);
  }
}
