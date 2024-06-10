package com.shepherdjerred.capstone.ai;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.turn.Turn;

public interface QuoridorAi {
  Turn calculateBestTurn(Match match);
}
