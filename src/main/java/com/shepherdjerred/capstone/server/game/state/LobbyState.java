package com.shepherdjerred.capstone.server.game.state;

import com.shepherdjerred.capstone.common.player.HumanPlayer;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.Match;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.server.event.FillSlotsWithAiEvent;
import com.shepherdjerred.capstone.server.event.MatchStartedEvent;
import com.shepherdjerred.capstone.server.event.PlayerInformationReceivedEvent;
import com.shepherdjerred.capstone.server.event.PlayerJoinEvent;
import com.shepherdjerred.capstone.server.event.StartGameEvent;
import com.shepherdjerred.capstone.server.game.GameLogic;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LobbyState extends AbstractGameServerState {

  public LobbyState(GameLogic gameLogic,
      EventBus<Event> eventBus) {
    super(gameLogic, eventBus);
  }

  @Override
  protected EventHandlerFrame<Event> createEventHandlerFrame() {
    var frame = new EventHandlerFrame<>();

    frame.registerHandler(PlayerInformationReceivedEvent.class, (event) -> {
      log.info("PLAYER IS JOINING");

      var lobby = gameLogic.getGameState().getLobby();

      if (lobby.isFull()) {
        // TODO Send a message saying the server is full
        event.getConnection().sendPacket(null);
        event.getConnection().disconnect();
      }

      var element = lobby.getFreeElement();

      if (element.isEmpty()) {
        throw new IllegalStateException("No free element");
      }

      var playerInformation = event.getPlayerInformation();
      var player = new HumanPlayer(playerInformation.getUuid(),
          playerInformation.getName(),
          element.get());

      lobby = lobby.addPlayer(player);

      gameLogic.setGameState(gameLogic.getGameState().setLobby(lobby));

      eventBus.dispatch(new PlayerJoinEvent(player, event.getConnection()));
    });

    frame.registerHandler(FillSlotsWithAiEvent.class, (event) -> {
      var lobby = gameLogic.getGameState().getLobby();

      log.info("Filling slots..");

      var player = lobby.createAiPlayer();
      eventBus.dispatch(new PlayerJoinEvent(player, null));
      lobby = lobby.setPlayer(player, QuoridorPlayer.TWO);
      log.info("Added player");

      gameLogic.setGameState(gameLogic.getGameState().setLobby(lobby));
    });

    frame.registerHandler(StartGameEvent.class, (event) -> {
      var currentGameState = gameLogic.getGameState();
      var lobbySettings = currentGameState.getLobby().getLobbySettings();
      var matchSettings = lobbySettings.getMatchSettings();
      var map = lobbySettings.getGameMap();
      var boardSettings = new BoardSettings(map.getBoardSize(), matchSettings.getPlayerCount());
      var match = Match.from(matchSettings, boardSettings);

      var newGameState = currentGameState.setMatch(match);

      gameLogic.setGameState(newGameState);

      gameLogic.transitionState(new MatchServerState(gameLogic, eventBus));

      eventBus.dispatch(new MatchStartedEvent());
    });

    return frame;
  }
}
