package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeMenuView;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeSettingsView;

public class SnakeSettingsController extends SnakeSettingsView {

  public Label filename;

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

  public void selectLevel(ActionEvent actionEvent) {
  }

  public void changeFoodNumber(ScrollEvent scrollEvent) {
  }

  public void changeSpeed(ScrollEvent scrollEvent) {
  }
}
