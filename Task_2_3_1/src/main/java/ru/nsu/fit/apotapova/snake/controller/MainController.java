package ru.nsu.fit.apotapova.snake.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ru.nsu.fit.apotapova.snake.App;

public class MainController {

  public AnchorPane game;
  public AnchorPane settings;
  public AnchorPane menu;
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
    App.setMainController(this);
    gameController.setMainController(this);
    settingsController.setMainController(this);
    menuController.setMainController(this);
    selectMenuScene();
  }

}
