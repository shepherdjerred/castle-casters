package com.shepherdjerred.capstone.server.game.state;

import com.shepherdjerred.capstone.ai.alphabeta.AlphaBetaQuoridorAi;
import com.shepherdjerred.capstone.ai.evaluator.EvaluatorWeights;
import com.shepherdjerred.capstone.ai.evaluator.WeightedMatchEvaluator;
import com.shepherdjerred.capstone.common.player.AiPlayer;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.server.event.DoAiTurnEvent;
import com.shepherdjerred.capstone.server.event.DoTurnEvent;
import com.shepherdjerred.capstone.server.event.MatchStartedEvent;
import com.shepherdjerred.capstone.server.event.TryDoTurnEvent;
import com.shepherdjerred.capstone.server.game.GameLogic;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MatchServerState extends AbstractGameServerState {

  public MatchServerState(GameLogic gameLogic,
      EventBus<Event> eventBus) {
    super(gameLogic, eventBus);
  }

  @Override
  protected EventHandlerFrame<Event> createEventHandlerFrame() {
    var frame = new EventHandlerFrame<>();

    frame.registerHandler(MatchStartedEvent.class, (event) -> {
      var gameState = gameLogic.getGameState();
      var match = gameState.getMatch();
      var nextPlayerId = match.getActivePlayerId();
      var nextPlayer = gameState.getLobby().getPlayer(nextPlayerId);

      log.info(gameState.getLobby());
      log.info(nextPlayer);
    });

    frame.registerHandler(DoAiTurnEvent.class, (event) -> {
      var gameState = gameLogic.getGameState();

      var match = gameState.getMatch();
      var ai = new AlphaBetaQuoridorAi(new WeightedMatchEvaluator(new EvaluatorWeights(
          9612.407041694314,
          -7288.691596308785,
          9786.056427421212,
          2396.69915479313,
          476.91303038346996
      )), 2);

      var turn = ai.calculateBestTurn(match);
      eventBus.dispatch(new TryDoTurnEvent(turn));
    });

    frame.registerHandler(TryDoTurnEvent.class, (event) -> {
      var gameState = gameLogic.getGameState();

      var match = gameState.getMatch();
      var turn = event.getTurn();

      try {
        var newMatch = match.doTurn(turn);
        var newGameState = gameState.setMatch(newMatch);

        gameLogic.setGameState(newGameState);

        eventBus.dispatch(new DoTurnEvent(turn));

        var nextPlayerId = newMatch.getActivePlayerId();
        var nextPlayer = gameState.getLobby().getPlayer(nextPlayerId);

        log.info(gameState.getLobby());
        log.info(gameState.getMatch());

        if (nextPlayer instanceof AiPlayer) {
          log.info("Doing AI turn...");
          eventBus.dispatch(new DoAiTurnEvent(nextPlayer));
        } else {
          log.info("Next player is not an AI " + nextPlayer);
        }
      } catch (Exception e) {
        log.error("Error doing turn", e);
      }
    });

    return frame;
  }
}
