package com.shepherdjerred.capstone.engine.scene.position;

import com.shepherdjerred.capstone.engine.object.SceneObjectDimensions;
import com.shepherdjerred.capstone.engine.window.WindowSize;
import com.shepherdjerred.capstone.engine.scene.SceneCoordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class WindowRelativeScenePositioner implements ScenePositioner {

  @Getter
  private final HorizontalPosition horizontalPosition;
  @Getter
  private final VerticalPosition verticalPosition;
  @Setter
  @Getter
  private SceneCoordinateOffset offset;
  private final float z;

  @Override
  public SceneCoordinate getSceneCoordinate(WindowSize windowSize,
      SceneObjectDimensions dimensions) {
    var x = getXCoordinate(windowSize, dimensions.getWidth()) + offset.getXOffset();
    var y = getYCoordinate(windowSize, dimensions.getHeight()) + offset.getYOffset();
    return new SceneCoordinate(x, y, z);
  }

  private float getXCoordinate(WindowSize windowSize, int width) {
    if (horizontalPosition == HorizontalPosition.LEFT) {
      return 0;
    } else if (horizontalPosition == HorizontalPosition.RIGHT) {
      return windowSize.getWidth() - width;
    } else if (horizontalPosition == HorizontalPosition.CENTER) {
      return (windowSize.getWidth() - width) / 2;
    } else {
      throw new UnsupportedOperationException();
    }
  }

  private float getYCoordinate(WindowSize windowSize, int height) {
    if (verticalPosition == VerticalPosition.TOP) {
      return 0;
    } else if (verticalPosition == VerticalPosition.BOTTOM) {
      return windowSize.getHeight() - height;
    } else if (verticalPosition == VerticalPosition.CENTER) {
      return (windowSize.getHeight() - height) / 2;
    } else {
      throw new UnsupportedOperationException();
    }
  }

  public enum HorizontalPosition {
    LEFT, RIGHT, CENTER
  }

  public enum VerticalPosition {
    TOP, BOTTOM, CENTER
  }
}
