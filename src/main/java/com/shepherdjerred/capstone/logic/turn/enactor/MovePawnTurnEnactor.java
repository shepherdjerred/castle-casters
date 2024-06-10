package com.shepherdjerred.capstone.logic.turn.enactor;

import com.shepherdjerred.capstone.logic.board.QuoridorBoard;
import com.shepherdjerred.capstone.logic.turn.MovePawnTurn;
import com.shepherdjerred.capstone.logic.turn.Turn;

public class MovePawnTurnEnactor implements TurnEnactor {

  /**
   * Takes the steps to transform a given board state by the parameters in a turn
   *
   * @param turn The turn to use when transforming the board
   * @param board The board state
   * @return The board state transformed by the turn
   */
  @Override
  public QuoridorBoard enactTurn(Turn turn, QuoridorBoard board) {
    if (turn instanceof MovePawnTurn) {
      return enactMovePawnTurn((MovePawnTurn) turn, board);
    } else {
      throw new IllegalArgumentException("Turn is not a NormalMovePawnTurn " + turn);
    }
  }

  private QuoridorBoard enactMovePawnTurn(MovePawnTurn turn, QuoridorBoard board) {
    return board.movePawn(turn.getCauser(), turn.getDestination());
  }
}
