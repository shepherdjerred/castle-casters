package com.shepherdjerred.capstone.server.network.broadcast.netty;

import com.shepherdjerred.capstone.common.lobby.Lobby;
import com.shepherdjerred.capstone.events.Event;
import com.shepherdjerred.capstone.events.EventBus;
import com.shepherdjerred.capstone.events.handlers.EventHandlerFrame;
import com.shepherdjerred.capstone.network.packet.packets.ServerBroadcastPacket;
import com.shepherdjerred.capstone.server.event.LobbyUpdatedEvent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NettyBroadcastBootstrap implements Runnable {

  private final SocketAddress address;
  private final EventBus<Event> eventBus;
  private EventLoopGroup group;
  private Lobby lobby;
  private final EventHandlerFrame<Event> eventHandlerFrame;

  public NettyBroadcastBootstrap(SocketAddress address,
      Lobby lobby,
      EventBus<Event> eventBus) {
    this.address = address;
    this.eventBus = eventBus;
    this.lobby = lobby;
    this.eventHandlerFrame = new EventHandlerFrame<>();
    createHandlerFrame();
    eventBus.registerHandlerFrame(eventHandlerFrame);
  }

  private void createHandlerFrame() {
    eventHandlerFrame.registerHandler(LobbyUpdatedEvent.class,
        (event) -> lobby = event.getNewLobby());
  }

  @Override
  public void run() {
    group = new NioEventLoopGroup(0,
        new DefaultThreadFactory("SERVER_BROADCAST_THREAD_POOL"));

    try {
      var bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioDatagramChannel.class)
          .handler(new NettyBroadcastInitializer())
          .option(ChannelOption.SO_BROADCAST, true)
          .option(ChannelOption.SO_REUSEADDR, true);

      var channel = bootstrap.bind(address).sync().channel();

      group.scheduleAtFixedRate(() -> {
            log.trace("Broadcasting: " + lobby);
            channel.writeAndFlush(new ServerBroadcastPacket(lobby));
          },
          0,
          2,
          TimeUnit.SECONDS);

      channel.closeFuture().sync();

    } catch (InterruptedException e) {
      log.error(e);
    } finally {
      shutdown();
    }
  }

  public void shutdown() {
    eventBus.removeHandlerFrame(eventHandlerFrame);
    group.shutdownGracefully().syncUninterruptibly();
  }
}
