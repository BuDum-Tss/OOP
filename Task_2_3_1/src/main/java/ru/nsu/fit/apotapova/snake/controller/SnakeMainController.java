package ru.nsu.fit.apotapova.snake.controller;

import java.util.List;
import javafx.fxml.FXML;
import ru.nsu.fit.apotapova.snake.App;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.SnakeGameController;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.SnakeMenuController;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.SnakeSettingsController;
import ru.nsu.fit.apotapova.snake.view.scene.SnakeMainView;

public class SnakeMainController extends SnakeMainView {

  @FXML
  private SnakeMenuController menuController;
  @FXML
  private SnakeSettingsController settingsController;
  @FXML
  private SnakeGameController gameController;

  @FXML
  public void initialize() {
    mainPane.setStyle("-fx-background-color: #000019");
    App.setMainController(this);
    gameController.setMainController(this);
    settingsController.setMainController(this);
    menuController.setMainController(this);
    setSceneList(List.of(menuController, settingsController, gameController));
  }
}
