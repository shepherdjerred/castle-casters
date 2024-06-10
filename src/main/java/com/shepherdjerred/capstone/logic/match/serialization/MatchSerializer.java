package com.shepherdjerred.capstone.logic.match.serialization;

import com.shepherdjerred.capstone.logic.match.Match;

public interface MatchSerializer {
  byte[] toBytes(Match match);

  Match fromBytes(byte[] bytes);
}
