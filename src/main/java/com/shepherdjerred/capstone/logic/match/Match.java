package com.shepherdjerred.capstone.logic.match;

import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.board.QuoridorBoard;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.logic.turn.enactor.MatchTurnEnactor;
import com.shepherdjerred.capstone.logic.turn.enactor.TurnEnactorFactory;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidatorFactory;
import lombok.Getter;

/**
 * A match of Quoridor.
 */
// TODO this could still be cleaned up more
@Getter
public record Match(QuoridorBoard board, MatchSettings matchSettings, WallBank wallBank, MatchStatus matchStatus,
                    MatchHistory matchHistory, MatchTurnEnactor matchTurnEnactor,
                    ActivePlayerTracker activePlayerTracker) {

  public static Match from(MatchSettings matchSettings, BoardSettings boardSettings) {
    var board = QuoridorBoard.from(boardSettings);
    return from(matchSettings, board);
  }

  // TODO board validation
  public static Match from(MatchSettings matchSettings, QuoridorBoard board) {
    var playerCount = matchSettings.playerCount();
    var startingPlayer = matchSettings.startingQuoridorPlayer();
    var wallPool = WallBank.from(matchSettings.playerCount(),
        matchSettings.wallsPerPlayer());
    var matchStatus = new MatchStatus(QuoridorPlayer.NULL, Status.IN_PROGRESS);
    var matchHistory = new MatchHistory();
    var activePlayerTracker = new ActivePlayerTracker(startingPlayer, playerCount);
    var matchTurnEnactor = new MatchTurnEnactor(new TurnEnactorFactory(),
        new TurnValidatorFactory(),
        new MatchStatusUpdater(new PlayerGoals()));

    return new Match(board,
        matchSettings,
        wallPool,
        matchStatus,
        matchHistory,
        matchTurnEnactor,
        activePlayerTracker);
  }

  /**
   * Processes a turn without validation. Use only with turns already validated against this Match.
   * Exists for performance
   */
  public Match doTurnUnchecked(Turn turn) {
    return matchTurnEnactor.enactTurnUnchecked(turn, this);
  }

  public Match doTurn(Turn turn) {
    return matchTurnEnactor.enactTurn(turn, this);
  }

  public int getWallsLeft(QuoridorPlayer quoridorPlayer) {
    return wallBank.getWallsLeft(quoridorPlayer);
  }

  public QuoridorPlayer getActivePlayerId() {
    return activePlayerTracker.getActivePlayer();
  }

  public QuoridorPlayer getNextActivePlayerId() {
    return activePlayerTracker.getNextActivePlayerId();
  }
}
