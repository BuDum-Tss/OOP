package ru.nsu.fit.apotapova.snake.controller;

import javafx.scene.layout.AnchorPane;

public abstract class SceneController {
  public AnchorPane scene;
  protected MainController mainController;

  void setVisible(boolean value) {
    scene.setVisible(value);
    scene.setDisable(!value);
  }

  void setMainController(MainController mainController) {
    this.mainController=mainController;
  }
}
