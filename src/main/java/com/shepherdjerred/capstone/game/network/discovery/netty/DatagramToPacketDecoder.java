package com.shepherdjerred.capstone.game.network.discovery.netty;

import com.shepherdjerred.capstone.game.network.discovery.ServerInformation;
import com.shepherdjerred.capstone.network.packet.packets.ServerBroadcastPacket;
import com.shepherdjerred.capstone.network.packet.serialization.PacketJsonSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class DatagramToPacketDecoder extends MessageToMessageDecoder<DatagramPacket> {

  private final PacketJsonSerializer serializer;

  @Override
  protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out)
      throws Exception {
    var bytes = new byte[msg.content().readableBytes()];
    msg.content().readBytes(bytes);
    var packet = serializer.fromBytes(bytes);
    var broadcastPacket = (ServerBroadcastPacket) packet;
    var info = new ServerInformation(msg.sender(), broadcastPacket.getLobby());
    out.add(info);
  }
}
