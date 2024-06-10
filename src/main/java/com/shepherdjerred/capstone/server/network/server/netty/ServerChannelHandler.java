package com.shepherdjerred.capstone.server.network.server.netty;

import com.shepherdjerred.capstone.network.packet.packets.Packet;
import com.shepherdjerred.capstone.server.network.event.events.ClientConnectedEvent;
import com.shepherdjerred.capstone.server.network.event.events.ClientDisconnectedEvent;
import com.shepherdjerred.capstone.server.network.event.events.NetworkEvent;
import com.shepherdjerred.capstone.server.network.event.events.PacketReceivedEvent;
import com.shepherdjerred.capstone.server.network.server.Connection;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Forwards channel event as event in a queue.
 */
@Log4j2
@RequiredArgsConstructor
public class ServerChannelHandler extends ChannelDuplexHandler {

  private Connection connection;
  private Channel channel;
  private final ConcurrentLinkedQueue<NetworkEvent> eventQueue;

  @Override
  public void channelActive(ChannelHandlerContext context) {
    this.channel = context.pipeline().channel();
    connection = new NettyConnection(this);
    eventQueue.add(new ClientConnectedEvent(connection));
  }

  @Override
  public void channelRead(ChannelHandlerContext context, Object message) {
    var packet = (Packet) message;
    var event = new PacketReceivedEvent(connection, packet);
    eventQueue.add(event);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    eventQueue.add(new ClientDisconnectedEvent(connection));
  }

  public void send(Object object) {
    if (!channel.isActive()) {
      log.error("Channel isn't connected");
    }
    channel.writeAndFlush(object);
  }

  public void disconnect() {
    channel.disconnect();
  }
}
