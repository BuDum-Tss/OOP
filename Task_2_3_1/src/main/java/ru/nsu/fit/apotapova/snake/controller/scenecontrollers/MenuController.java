package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import ru.nsu.fit.apotapova.snake.controller.SceneController;

public class MenuController extends SceneController {
  public void startBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectGameScene();
  }

  public void settingsBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectSettingsScene();
  }
}
