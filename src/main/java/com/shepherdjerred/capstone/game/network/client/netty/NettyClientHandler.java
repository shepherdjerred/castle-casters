package com.shepherdjerred.capstone.game.network.client.netty;

import com.shepherdjerred.capstone.game.network.event.NetworkEvent;
import com.shepherdjerred.capstone.game.network.event.PacketReceivedEvent;
import com.shepherdjerred.capstone.game.network.event.ServerConnectedEvent;
import com.shepherdjerred.capstone.game.network.event.ServerDisconnectedEvent;
import com.shepherdjerred.capstone.network.packet.packets.Packet;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class NettyClientHandler extends ChannelDuplexHandler {

  private final ConcurrentLinkedQueue<NetworkEvent> eventQueue;

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    eventQueue.add(new ServerConnectedEvent());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    eventQueue.add(new ServerDisconnectedEvent());
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    var packet = (Packet) msg;
    log.info("Received a packet: " + packet);
    eventQueue.add(new PacketReceivedEvent(packet));
  }
}
