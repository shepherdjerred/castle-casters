package com.shepherdjerred.capstone.game.network.client.netty;

import com.shepherdjerred.capstone.game.network.event.NetworkEvent;
import com.shepherdjerred.capstone.network.packet.packets.Packet;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.net.SocketAddress;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NettyClientBootstrap {

  private EventLoopGroup eventLoopGroup;
  private Channel channel;
  private ConcurrentLinkedQueue<NetworkEvent> eventQueue = new ConcurrentLinkedQueue<>();

  public void connect(SocketAddress address) {
    eventLoopGroup = new NioEventLoopGroup(2,
        new DefaultThreadFactory("CLIENT_NETWORK_THREAD_POOL"));

    try {
      var bootstrap = new Bootstrap();
      bootstrap.group(eventLoopGroup)
          .channel(NioSocketChannel.class)
          .handler(new NettyClientInitializer(eventQueue))
          .option(ChannelOption.SO_REUSEADDR, true)
          .option(ChannelOption.SO_KEEPALIVE, true);

      channel = bootstrap.connect(address).sync().channel();

      channel.closeFuture().sync();
    } catch (InterruptedException e) {
      log.error(e);
    } finally {
      cleanup();
    }
  }

  public void send(Packet packet) {
    channel.writeAndFlush(packet);
  }

  public void cleanup() {
    eventLoopGroup.shutdownGracefully();
  }

  public Optional<NetworkEvent> getLatestEvent() {
    if (eventQueue.size() > 0) {
      return Optional.of(eventQueue.poll());
    } else {
      return Optional.empty();
    }
  }
}
