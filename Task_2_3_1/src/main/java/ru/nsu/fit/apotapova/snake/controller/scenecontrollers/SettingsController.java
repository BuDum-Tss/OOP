package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import javafx.event.ActionEvent;
import ru.nsu.fit.apotapova.snake.controller.SceneController;

public class SettingsController extends SceneController {

  public void saveBtn(ActionEvent actionEvent) {
  }

  public void closeBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectMenuScene();
  }

  public void defaultBtn(ActionEvent actionEvent) {
  }
}
