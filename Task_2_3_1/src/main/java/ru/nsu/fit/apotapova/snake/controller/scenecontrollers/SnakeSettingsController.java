package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeMenuView;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeSettingsView;

public class SnakeSettingsController extends SnakeSettingsView {

  public void saveBtn(ActionEvent actionEvent) {
  }

  public void closeBtn(ActionEvent actionEvent) {
    mainController.selectScene(SnakeMenuView.class);
  }

  public void defaultBtn(ActionEvent actionEvent) {
  }

  @FXML
  public void initialize() {
    setVisible(false);
  }
}
