package com.shepherdjerred.capstone.server.network.broadcast.netty;

import static com.shepherdjerred.capstone.common.Constants.DISCOVERY_PORT;

import com.shepherdjerred.capstone.network.netty.handlers.ExceptionLoggerHandler;
import com.shepherdjerred.capstone.network.packet.serialization.PacketJsonSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import java.net.InetSocketAddress;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NettyBroadcastInitializer extends ChannelInitializer<NioDatagramChannel> {

  @Override
  protected void initChannel(NioDatagramChannel datagramChannel) {
    var pipeline = datagramChannel.pipeline();
    var serializer = new PacketJsonSerializer();

    pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 4));
    pipeline.addLast(new BroadcastPacketEncoder(new InetSocketAddress("255.255.255.255",
        DISCOVERY_PORT), serializer));
    pipeline.addLast(new LoggingHandler());
    pipeline.addLast(new ExceptionLoggerHandler());
  }
}
