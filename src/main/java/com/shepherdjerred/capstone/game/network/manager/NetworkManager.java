package com.shepherdjerred.capstone.game.network.manager;

import com.shepherdjerred.capstone.common.Constants;
import com.shepherdjerred.capstone.common.GameState;
import com.shepherdjerred.capstone.game.network.client.NetworkClient;
import com.shepherdjerred.capstone.game.network.discovery.ServerDiscoverer;
import com.shepherdjerred.capstone.game.network.discovery.netty.NettyServerDiscoverer;
import com.shepherdjerred.capstone.game.network.manager.event.ConnectServerEvent;
import com.shepherdjerred.capstone.game.network.manager.event.ShutdownNetworkEvent;
import com.shepherdjerred.capstone.game.network.manager.event.StartDiscoveryEvent;
import com.shepherdjerred.capstone.game.network.manager.event.StartServerEvent;
import com.shepherdjerred.capstone.game.network.manager.event.StopClientEvent;
import com.shepherdjerred.capstone.game.network.manager.event.StopDiscoveryEvent;
import com.shepherdjerred.capstone.game.network.manager.event.StopServerEvent;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.server.GameServer;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class NetworkManager {

  private final EventBus<Event> eventBus;
  private final EventHandlerFrame<Event> eventHandlerFrame;
  private GameServer gameServer = null;
  private NetworkClient networkClient = null;
  private ServerDiscoverer discoverer = null;
  private Thread serverThread;
  private Thread clientThread;
  private Thread discoveryThread;

  public NetworkManager(EventBus<Event> eventBus) {
    this.eventBus = eventBus;
    this.eventHandlerFrame = new EventHandlerFrame<>();
    initializeEventHandlerFrame();
  }

  private void initializeEventHandlerFrame() {
    eventHandlerFrame.registerHandler(StartServerEvent.class, (event) -> {
      createServer(event.getGameState());
    });
    eventHandlerFrame.registerHandler(StopClientEvent.class, (event) -> {
      stopClient();
    });
    eventHandlerFrame.registerHandler(StopServerEvent.class, (event) -> {
      stopServer();
    });
    eventHandlerFrame.registerHandler(ConnectServerEvent.class, (event) -> {
      connectClient(event.getAddress());
    });
    eventHandlerFrame.registerHandler(StartDiscoveryEvent.class, (event) -> {
      startDiscovery();
    });
    eventHandlerFrame.registerHandler(StopDiscoveryEvent.class, (event) -> {
      stopDiscovery();
    });
    eventHandlerFrame.registerHandler(ShutdownNetworkEvent.class, (event) -> {
      shutdown();
    });
  }

  public void initialize() {
    eventBus.registerHandlerFrame(eventHandlerFrame);
  }

  public void update() {
    if (networkClient != null) {
      networkClient.update();
    }
    if (discoverer != null) {
      var event = discoverer.getEvent();
      event.ifPresent(eventBus::dispatch);
    }
  }

  private void connectClient(SocketAddress address) {
    networkClient = new NetworkClient(eventBus);
    clientThread = new Thread(() -> networkClient.connect(address), "CLIENT_NETWORK");
    clientThread.start();
  }

  private void createServer(GameState gameState) {
    gameServer = new GameServer(gameState,
        new InetSocketAddress(Constants.GAME_PORT),
        new InetSocketAddress(Constants.DISCOVERY_PORT));
    serverThread = new Thread(gameServer, "GAME_SERVER");
    serverThread.start();
  }

  private void startDiscovery() {
    discoverer = new NettyServerDiscoverer();
    discoveryThread = new Thread(discoverer, "DISCOVERY");
    discoveryThread.start();
  }

  private void stopDiscovery() {
    if (discoverer != null) {
      discoverer.stop();
      discoverer = null;
    }
  }

  private void stopServer() {
    if (gameServer != null) {
      gameServer.shutdown();
      gameServer = null;
    }
  }

  private void stopClient() {
    if (networkClient != null) {
      networkClient.shutdown();
      networkClient = null;
    }
  }

  public void shutdown() {
    eventBus.removeHandlerFrame(eventHandlerFrame);
    stopDiscovery();
    stopClient();
    stopServer();
  }

}
