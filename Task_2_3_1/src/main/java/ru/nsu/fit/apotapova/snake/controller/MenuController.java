package ru.nsu.fit.apotapova.snake.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class MenuController extends SceneController {
  public AnchorPane scene;
  public void startBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectGameScene();
  }

  public void settingsBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectSettingsScene();
  }
}
