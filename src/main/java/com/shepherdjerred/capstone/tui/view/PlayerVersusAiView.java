package com.shepherdjerred.capstone.tui.view;

import com.shepherdjerred.capstone.ai.QuoridorAi;
import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.match.MatchFormatter;
import com.shepherdjerred.capstone.logic.match.MatchSettings;
import com.shepherdjerred.capstone.logic.match.MatchStatus.Status;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.NormalMovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.logic.turn.notation.NotationToTurnConverter;
import com.shepherdjerred.capstone.logic.turn.notation.TurnToNotationConverter;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class PlayerVersusAiView implements View {

  private final Scanner scanner;
  private final BoardSettings boardSettings;
  private final MatchSettings matchSettings;
  private final QuoridorAi quoridorAi;
  private final QuoridorPlayer aiPlayer;

  @Override
  public Optional<View> display() {
    var formatter = new MatchFormatter();
    var match = Match.from(matchSettings, boardSettings);
    var shouldContinue = true;

    while (shouldContinue) {
      System.out.println(formatter.matchToString(match));
      var activePlayer = match.getActivePlayerId();
      if (activePlayer == aiPlayer) {
        System.out.println("Starting AI");
        var startTime = Instant.now();
        var turn = quoridorAi.calculateBestTurn(match);
        var endTime = Instant.now();
        var elapsed = Duration.between(startTime, endTime);
        match = match.doTurn(turn);

        var converter = new TurnToNotationConverter();
        System.out.println("Time taken: " + elapsed.toMillis() / 1000 + "s");
        System.out.println("AI turn: " + converter.convert(turn));
      } else {
        System.out.println("Available commands: UNDO, EXIT");
        System.out.println("Enter a turn in Quoridor notation (i.e. a1 or a1v) or a command");
        var input = scanner.next();

        if (input.equals("UNDO")) {
          if (match.getMatchHistory().isEmpty()) {
            System.out.println("No history to undo");
            continue;
          }
          match = match.getMatchHistory().pop().getMatch().getMatchHistory().pop().getMatch();
          continue;
        } else if (input.equals("EXIT")) {
          return Optional.of(new MainMenuView(scanner));
        }

        Turn turn = null;

        try {
          var converter = new NotationToTurnConverter();
          turn = converter.convert(input);
        } catch (Exception e) {
          log.error("Error converting notation string to turn", e);
        }

        if (turn instanceof MovePawnTurn) {
          var playerPawn = match.getBoard().getPawnLocation(activePlayer);
          turn = new NormalMovePawnTurn(activePlayer,
              playerPawn,
              ((MovePawnTurn) turn).getDestination());
        } else if (turn instanceof PlaceWallTurn) {
          turn = new PlaceWallTurn(activePlayer, ((PlaceWallTurn) turn).getLocation());
        }

        if (turn != null) {
          match = match.doTurnUnchecked(turn);
        } else {
          log.error("Turn is null");
        }
      }

      if (match.getMatchStatus().getStatus() == Status.VICTORY) {
        shouldContinue = false;
      }
    }

    System.out.println("WINNER: " + match.getMatchStatus().getVictor());

    return Optional.of(new MainMenuView(scanner));
  }
}
