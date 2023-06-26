package ru.nsu.fit.apotapova.snake.controller;

import java.util.List;
import javafx.fxml.FXML;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.SnakeGameController;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.SnakeMenuController;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.SnakeSettingsController;
import ru.nsu.fit.apotapova.snake.view.scene.SnakeMainView;

/**
 * Snake main controller.
 */
public class SnakeMainController extends SnakeMainView {

  @FXML
  private SnakeMenuController menuController;
  @FXML
  private SnakeSettingsController settingsController;
  @FXML
  private SnakeGameController gameController;

  /**
   * Initializes main controller in all others controllers.
   */
  @FXML
  public void initialize() {
    gameController.setMainController(this);
    settingsController.setMainController(this);
    menuController.setMainController(this);
    setSceneList(List.of(menuController, settingsController, gameController));
  }
}
