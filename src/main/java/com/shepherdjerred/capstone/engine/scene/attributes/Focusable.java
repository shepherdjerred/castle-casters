package com.shepherdjerred.capstone.engine.scene.attributes;

public interface Focusable {

  void onFocus();

  void onUnfocus();

  boolean isFocused();
}
