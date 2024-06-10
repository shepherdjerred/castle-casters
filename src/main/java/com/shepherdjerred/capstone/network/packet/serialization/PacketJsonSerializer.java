package com.shepherdjerred.capstone.network.packet.serialization;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.shepherdjerred.capstone.common.player.AiPlayer;
import com.shepherdjerred.capstone.common.player.HumanPlayer;
import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.logic.turn.JumpPawnDiagonalTurn;
import com.shepherdjerred.capstone.logic.turn.JumpPawnStraightTurn;
import com.shepherdjerred.capstone.logic.turn.NormalMovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.PlaceWallTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;
import com.shepherdjerred.capstone.network.packet.packets.ConnectionAcceptedPacket;
import com.shepherdjerred.capstone.network.packet.packets.ConnectionRejectedPacket;
import com.shepherdjerred.capstone.network.packet.packets.DoTurnPacket;
import com.shepherdjerred.capstone.network.packet.packets.FillSlotsWithAiPacket;
import com.shepherdjerred.capstone.network.packet.packets.Packet;
import com.shepherdjerred.capstone.network.packet.packets.PlayerDescriptionPacket;
import com.shepherdjerred.capstone.network.packet.packets.PlayerJoinPacket;
import com.shepherdjerred.capstone.network.packet.packets.ServerBroadcastPacket;
import com.shepherdjerred.capstone.network.packet.packets.StartMatchPacket;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PacketJsonSerializer implements PacketSerializer {

  private Gson gson;

  public PacketJsonSerializer() {
    var packetTypeFactory = RuntimeTypeAdapterFactory.of(Packet.class, "packetType")
        .registerSubtype(ConnectionAcceptedPacket.class)
        .registerSubtype(ConnectionRejectedPacket.class)
        .registerSubtype(PlayerDescriptionPacket.class)
        .registerSubtype(PlayerJoinPacket.class)
        .registerSubtype(ServerBroadcastPacket.class)
        .registerSubtype(StartMatchPacket.class)
        .registerSubtype(DoTurnPacket.class)
        .registerSubtype(FillSlotsWithAiPacket.class);

    var turnTypeFactory = RuntimeTypeAdapterFactory.of(Turn.class, "turnTurn")
        .registerSubtype(NormalMovePawnTurn.class)
        .registerSubtype(JumpPawnDiagonalTurn.class)
        .registerSubtype(JumpPawnStraightTurn.class)
        .registerSubtype(PlaceWallTurn.class);

    var playerTypeFactory = RuntimeTypeAdapterFactory.of(Player.class, "playerType")
        .registerSubtype(HumanPlayer.class)
        .registerSubtype(AiPlayer.class);

    gson = new GsonBuilder()
        .enableComplexMapKeySerialization()
        .registerTypeAdapterFactory(packetTypeFactory)
        .registerTypeAdapterFactory(playerTypeFactory)
        .registerTypeAdapterFactory(turnTypeFactory)
        .create();
  }

  @Override
  public byte[] toBytes(Packet packet) {
    var packetAsJson = gson.toJson(packet, Packet.class);
    return packetAsJson.getBytes(Charsets.UTF_8);
  }

  @Override
  public Packet fromBytes(byte[] packetAsBytes) {
    var packetAsString = new String(packetAsBytes, Charsets.UTF_8);
    return gson.fromJson(packetAsString, new TypeToken<Packet>() {
    }.getType());
  }
}
