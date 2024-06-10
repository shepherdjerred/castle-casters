package com.shepherdjerred.capstone.server.game.state;

import com.shepherdjerred.capstone.common.lobby.LobbySettings.LobbyType;
import com.shepherdjerred.capstone.common.player.Element;
import com.shepherdjerred.capstone.common.player.HumanPlayer;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import com.shepherdjerred.capstone.server.event.FillSlotsWithAiEvent;
import com.shepherdjerred.capstone.server.event.PlayerInformationReceivedEvent;
import com.shepherdjerred.capstone.server.event.PlayerJoinEvent;
import com.shepherdjerred.capstone.server.game.GameLogic;
import com.shepherdjerred.capstone.server.network.manager.events.StartBroadcastEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PreLobbyState extends AbstractGameServerState {

  public PreLobbyState(GameLogic gameLogic,
      EventBus<Event> eventBus) {
    super(gameLogic, eventBus);
  }

  @Override
  protected EventHandlerFrame<Event> createEventHandlerFrame() {
    var frame = new EventHandlerFrame<>();

    frame.registerHandler(PlayerInformationReceivedEvent.class, event -> {
      var playerInformation = event.getPlayerInformation();
      var player = new HumanPlayer(playerInformation.getUuid(),
          playerInformation.getName(),
          Element.ICE);

      eventBus.dispatch(new PlayerJoinEvent(player, event.getConnection()));

      gameLogic.setHost(player);
      gameLogic.transitionState(new LobbyState(gameLogic, eventBus));

      var lobby = gameLogic.getGameState().getLobby();
      var lobbyType = lobby.getLobbySettings().getLobbyType();

      if (lobbyType == LobbyType.NETWORK) {
        eventBus.dispatch(new StartBroadcastEvent(lobby));
      }
    });

    frame.registerHandler(FillSlotsWithAiEvent.class, (event) -> {
      var lobby = gameLogic.getGameState().getLobby();

      log.info("Filling slots...");

      var player = lobby.createAiPlayer();
      eventBus.dispatch(new PlayerJoinEvent(player, null));
      lobby = lobby.setPlayer(player, QuoridorPlayer.TWO);
      log.info("Added player");

      gameLogic.setGameState(gameLogic.getGameState().setLobby(lobby));
    });

    return frame;
  }

}
