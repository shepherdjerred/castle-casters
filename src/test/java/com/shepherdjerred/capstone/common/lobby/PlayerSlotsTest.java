package com.shepherdjerred.capstone.common.lobby;


import com.shepherdjerred.capstone.logic.player.PlayerCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerSlotsTest {

  @Test
  public void getFreeSlots_returnsTwo_whenCreatingNewPlayerSlotsForTwoPlayers() {
    var playerSlots = PlayerSlots.forPlayerCount(PlayerCount.TWO);

    Assertions.assertEquals(2, playerSlots.getFreeSlots());
  }

  @Test
  public void getTakenSlots_returnsZero_whenCreatingNewPlayerSlotsForTwoPlayers() {
    var playerSlots = PlayerSlots.forPlayerCount(PlayerCount.TWO);

    Assertions.assertEquals(0, playerSlots.getTakenSlots());
  }
}
