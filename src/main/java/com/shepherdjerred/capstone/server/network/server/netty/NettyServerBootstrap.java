package com.shepherdjerred.capstone.server.network.server.netty;

import com.shepherdjerred.capstone.server.network.event.events.NetworkEvent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.net.SocketAddress;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NettyServerBootstrap implements Runnable {

  private final ConcurrentLinkedQueue<NetworkEvent> eventQueue;
  private final SocketAddress address;
  private EventLoopGroup group;

  public NettyServerBootstrap(SocketAddress address) {
    this.address = address;
    this.eventQueue = new ConcurrentLinkedQueue<>();
  }

  @Override
  public void run() {
    group = new NioEventLoopGroup(0,
        new DefaultThreadFactory("SERVER_NETWORK_THREAD_POOL"));

    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap()
          .group(group)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ServerChannelInitializer(eventQueue))
          .localAddress(address)
          .option(ChannelOption.SO_REUSEADDR, true);

      ChannelFuture channel = serverBootstrap.bind().sync();
      channel.channel().closeFuture().sync();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      shutdown();
    }
  }

  public Optional<NetworkEvent> getNextEvent() {
    if (eventQueue.size() > 0) {
      return Optional.of(eventQueue.poll());
    } else {
      return Optional.empty();
    }
  }

  public void shutdown() {
    group.shutdownGracefully().syncUninterruptibly();
  }
}
