package com.shepherdjerred.capstone.game.network.discovery.netty;

import com.shepherdjerred.capstone.game.network.event.NetworkEvent;
import com.shepherdjerred.capstone.network.netty.handlers.ExceptionLoggerHandler;
import com.shepherdjerred.capstone.network.packet.serialization.PacketJsonSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentLinkedQueue;

@AllArgsConstructor
public class DiscoveryChannelInitializer extends ChannelInitializer<DatagramChannel> {

  private final ConcurrentLinkedQueue<NetworkEvent> eventQueue;

  @Override
  protected void initChannel(DatagramChannel datagramChannel) {
    var pipeline = datagramChannel.pipeline();
    var serializer = new PacketJsonSerializer();

    pipeline.addLast(new DatagramToPacketDecoder(serializer));
    pipeline.addLast(new DiscoveryChannelInboundHandler(eventQueue));
    pipeline.addLast(new LoggingHandler());
    pipeline.addLast(new ExceptionLoggerHandler());
  }
}
