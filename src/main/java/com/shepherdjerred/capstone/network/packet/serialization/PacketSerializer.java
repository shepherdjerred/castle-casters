package com.shepherdjerred.capstone.network.packet.serialization;


import com.shepherdjerred.capstone.network.packet.packets.Packet;

public interface PacketSerializer {

  byte[] toBytes(Packet packet);

  Packet fromBytes(byte[] packetAsBytes);
}
