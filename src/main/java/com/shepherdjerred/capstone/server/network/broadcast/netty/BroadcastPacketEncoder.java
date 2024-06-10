package com.shepherdjerred.capstone.server.network.broadcast.netty;

import com.shepherdjerred.capstone.network.packet.packets.Packet;
import com.shepherdjerred.capstone.network.packet.serialization.PacketSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.net.InetSocketAddress;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class BroadcastPacketEncoder extends MessageToMessageEncoder<Packet> {

  private final InetSocketAddress address;
  private final PacketSerializer serializer;

  @Override
  protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) {
    var packetAsBytes = serializer.toBytes(packet);

    var buffer = ctx.alloc().buffer(packetAsBytes.length);
    buffer.writeBytes(packetAsBytes);

    var datagramPacket = new DatagramPacket(buffer, address);
    out.add(datagramPacket);
  }
}
