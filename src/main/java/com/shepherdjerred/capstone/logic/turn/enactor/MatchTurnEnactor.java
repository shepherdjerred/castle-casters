package com.shepherdjerred.capstone.logic.turn.enactor;

import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchHistoryEntry;
import com.shepherdjerred.capstone.logic.match.MatchStatusUpdater;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.logic.turn.exception.InvalidTurnException;
import com.shepherdjerred.capstone.logic.turn.validator.MatchTurnValidator;
import com.shepherdjerred.capstone.logic.turn.validator.TurnValidatorFactory;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

// TODO this needs to be cleaned up
@EqualsAndHashCode
@AllArgsConstructor
public class MatchTurnEnactor {

  private final TurnEnactorFactory turnEnactorFactory;
  private final TurnValidatorFactory turnValidatorFactory;
  private final MatchStatusUpdater matchStatusUpdater;

  public Match enactTurn(Turn turn, Match match) {
    var matchValidatorResult = new MatchTurnValidator().validate(match, turn);
    if (matchValidatorResult.isError()) {
      throw new InvalidTurnException(turn, matchValidatorResult);
    }

    var validator = turnValidatorFactory.getValidator(turn);
    var validatorResult = validator.validate(match, turn);
    if (validatorResult.isError()) {
      throw new InvalidTurnException(turn, validatorResult);
    } else {
      return enactTurnUnchecked(turn, match);
    }
  }

  public Match enactTurnUnchecked(Turn turn, Match match) {
    var enactor = turnEnactorFactory.getEnactor(turn);
    var board = match.getBoard();

    var newBoard = enactor.enactTurn(turn, board);
    var newMatchStatus = matchStatusUpdater.updateMatchStatus(turn, match);

    // TODO I think this would be better to do in the turn specific handler, but I'm not sure how that can be done well
    var newWallPool = match.getWallBank();
    if (turn instanceof PlaceWallTurn) {
      newWallPool = newWallPool.takeWall(turn.getCauser());
    }

    var matchHistory = match.getMatchHistory();
    var newHistory = matchHistory.push(new MatchHistoryEntry(match, turn));

    var matchSettings = match.getMatchSettings();

    var newActivePlayerTracker = match.getActivePlayerTracker().getNextActivePlayerTracker();

    return new Match(newBoard,
        matchSettings,
        newWallPool,
        newMatchStatus,
        newHistory,
        match.getMatchTurnEnactor(),
        newActivePlayerTracker);
  }
}
