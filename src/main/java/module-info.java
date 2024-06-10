module com.shepherdjerred.capstone.engine {
  requires static lombok;
  requires org.apache.logging.log4j;
  requires org.lwjgl.glfw;
  requires org.lwjgl.opengl;
  requires org.joml;
  requires org.lwjgl.stb;
  requires com.google.common;
  requires io.netty.all;
  requires org.lwjgl.openal;
  requires name.machine;
  requires ai.algorithms;
  requires io.jenetics.base;
  requires gson.extras;
  requires io.netty.transport;
  requires io.netty.codec;
  requires io.netty.handler;
  requires io.netty.common;
  requires com.google.gson;
  requires io.netty.buffer;

  opens com.shepherdjerred.capstone.logic.match to com.google.gson;
  opens com.shepherdjerred.capstone.logic.board to com.google.gson;
  opens com.shepherdjerred.capstone.logic.board.layout to com.google.gson;
  opens com.shepherdjerred.capstone.logic.board.pieces to com.google.gson;
  opens com.shepherdjerred.capstone.logic.player to com.google.gson;
  opens com.shepherdjerred.capstone.logic.turn.enactor to com.google.gson;
  opens com.shepherdjerred.capstone.logic.turn.validator to com.google.gson;
  opens com.shepherdjerred.capstone.logic.piece to com.google.gson;
}
