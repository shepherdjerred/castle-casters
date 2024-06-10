package com.shepherdjerred.capstone.engine.input.keyboard;

import static com.shepherdjerred.capstone.engine.input.keyboard.Key.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_0;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_3;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_4;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_5;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_6;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_7;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_8;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_9;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_I;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_J;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_K;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_M;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_N;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_O;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_T;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_U;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Y;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;

import java.util.Optional;

/**
 * Converts GLFW integer keycodes to a Key enum.
 */
public class GlfwKeyCodeConverter {

  public Optional<Key> fromGlfwKey(int glfwKey) {
    Key key = null;

    switch (glfwKey) {
      case GLFW_KEY_A:
        key = A;
        break;
      case GLFW_KEY_B:
        key = B;
        break;
      case GLFW_KEY_C:
        key = C;
        break;
      case GLFW_KEY_D:
        key = D;
        break;
      case GLFW_KEY_E:
        key = E;
        break;
      case GLFW_KEY_F:
        key = F;
        break;
      case GLFW_KEY_G:
        key = G;
        break;
      case GLFW_KEY_H:
        key = H;
        break;
      case GLFW_KEY_I:
        key = I;
        break;
      case GLFW_KEY_J:
        key = J;
        break;
      case GLFW_KEY_K:
        key = K;
        break;
      case GLFW_KEY_L:
        key = L;
        break;
      case GLFW_KEY_M:
        key = M;
        break;
      case GLFW_KEY_N:
        key = N;
        break;
      case GLFW_KEY_O:
        key = O;
        break;
      case GLFW_KEY_P:
        key = P;
        break;
      case GLFW_KEY_Q:
        key = Q;
        break;
      case GLFW_KEY_R:
        key = R;
        break;
      case GLFW_KEY_S:
        key = S;
        break;
      case GLFW_KEY_T:
        key = T;
        break;
      case GLFW_KEY_U:
        key = U;
        break;
      case GLFW_KEY_V:
        key = V;
        break;
      case GLFW_KEY_W:
        key = W;
        break;
      case GLFW_KEY_X:
        key = X;
        break;
      case GLFW_KEY_Y:
        key = Y;
        break;
      case GLFW_KEY_Z:
        key = Z;
        break;
      case GLFW_KEY_SPACE:
        key = SPACE;
        break;
      case GLFW_KEY_1:
        key = ONE;
        break;
      case GLFW_KEY_2:
        key = TWO;
        break;
      case GLFW_KEY_3:
        key = THREE;
        break;
      case GLFW_KEY_4:
        key = FOUR;
        break;
      case GLFW_KEY_5:
        key = FIVE;
        break;
      case GLFW_KEY_6:
        key = SIX;
        break;
      case GLFW_KEY_7:
        key = SEVEN;
        break;
      case GLFW_KEY_8:
        key = EIGHT;
        break;
      case GLFW_KEY_9:
        key = NINE;
        break;
      case GLFW_KEY_0:
        key = ZERO;
        break;
      case GLFW_KEY_UP:
        key = UP;
        break;
      case GLFW_KEY_DOWN:
        key = DOWN;
        break;
      case GLFW_KEY_LEFT:
        key = LEFT;
        break;
      case GLFW_KEY_RIGHT:
        key = RIGHT;
        break;
      case GLFW_KEY_ESCAPE:
        key = ESCAPE;
        break;
    }

    if (key == null) {
      return Optional.empty();
    } else {
      return Optional.of(key);
    }
  }
}
