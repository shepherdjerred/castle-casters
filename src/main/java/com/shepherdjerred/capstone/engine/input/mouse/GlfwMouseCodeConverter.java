package com.shepherdjerred.capstone.engine.input.mouse;

import static com.shepherdjerred.capstone.engine.input.mouse.MouseButton.*;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;

import java.util.Optional;

/**
 * Converts GLFW mouse codes to a MouseButton enum.
 */
public class GlfwMouseCodeConverter {
  public Optional<MouseButton> fromGlfwButton(int glfwButton) {
    MouseButton mouseButton = null;

    switch (glfwButton) {
      case GLFW_MOUSE_BUTTON_LEFT:
        mouseButton = LEFT;
        break;
      case GLFW_MOUSE_BUTTON_RIGHT:
        mouseButton = RIGHT;
        break;
    }

    if (mouseButton == null) {
      return Optional.empty();
    } else {
      return Optional.of(mouseButton);
    }
  }
}
