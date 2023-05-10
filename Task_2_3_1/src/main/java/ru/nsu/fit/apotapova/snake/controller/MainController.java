package ru.nsu.fit.apotapova.snake.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ru.nsu.fit.apotapova.snake.App;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.GameController;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.MenuController;
import ru.nsu.fit.apotapova.snake.controller.scenecontrollers.SettingsController;

public class MainController {

  public AnchorPane game;
  public AnchorPane settings;
  public AnchorPane menu;
  public StackPane mainPane;
  @FXML
  private MenuController menuController;
  @FXML
  private SettingsController settingsController;
  @FXML
  private GameController gameController;
  public void selectMenuScene() {
    settingsController.setVisible(false);
    gameController.setVisible(false);
    menuController.setVisible(true);
  }

  public void selectGameScene() {
    gameController.newGame();
    settingsController.setVisible(false);
    gameController.setVisible(true);
    menuController.setVisible(false);
  }

  public void selectSettingsScene() {
    settingsController.setVisible(true);
    gameController.setVisible(false);
    menuController.setVisible(false);
  }

  @FXML
  public void initialize() {
    mainPane.setBackground(new Background(new BackgroundFill(Color.color(0,0,0.1,1),null,null)));
    App.setMainController(this);
    gameController.setMainController(this);
    settingsController.setMainController(this);
    menuController.setMainController(this);

    selectMenuScene();
  }

}
