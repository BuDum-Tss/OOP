package ru.nsu.fit.apotapova.snake.view.scene;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public abstract class SnakeMainView extends MainView {

  @FXML
  public StackPane mainPane;
  @FXML
  public AnchorPane game;
  @FXML
  public AnchorPane settings;
  @FXML
  public AnchorPane menu;
}
