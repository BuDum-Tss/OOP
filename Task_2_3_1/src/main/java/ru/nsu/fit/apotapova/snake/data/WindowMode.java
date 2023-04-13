package ru.nsu.fit.apotapova.snake.data;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.stage.Stage;

public enum WindowMode {
  WINDOW("Window", stage -> {
    stage.setMaximized(false);
    stage.setFullScreen(false);
  }),
  FULLSCREEN("Full-screen", stage -> {
    stage.setFullScreen(true);
    stage.setMaximized(false);
  }),
  MAXIMAL("Maximal", stage -> {
    stage.setMaximized(true);
    stage.setFullScreen(false);
  });

  private static final Map<String, WindowMode> map = Arrays.stream(values())
      .collect(
          Collectors.toMap(mode -> mode.type, mode -> mode));
  private final String type;
  private final Consumer<Stage> action;

  WindowMode(String type, Consumer<Stage> action) {
    this.type = type;
    this.action = action;
  }

  public WindowMode getMode(String type) {
    return map.get(type);
  }
  public void setMode(Stage stage) {
    action.accept(stage);
  }
}
