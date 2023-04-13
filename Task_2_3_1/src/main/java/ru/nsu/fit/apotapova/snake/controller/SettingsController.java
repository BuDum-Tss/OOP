package ru.nsu.fit.apotapova.snake.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class SettingsController extends SceneController {

  public AnchorPane scene;
  public void saveBtn(ActionEvent actionEvent) {
  }

  public void closeBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectMenuScene();
  }

  public void defaultBtn(ActionEvent actionEvent) {
  }
}
