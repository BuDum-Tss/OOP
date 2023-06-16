package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeGameView;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeMenuView;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeSettingsView;

public class SnakeMenuController extends SnakeMenuView {

  @FXML
  public void startBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectScene(SnakeGameView.class);
  }

  @FXML
  public void settingsBtn(ActionEvent actionEvent) {
    setVisible(false);
    mainController.selectScene(SnakeSettingsView.class);
  }

  @FXML
  public void initialize() {
    setVisible(true);
  }
}
