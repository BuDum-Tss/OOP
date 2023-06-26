package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import java.io.File;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.nsu.fit.apotapova.snake.utils.Configuration;
import ru.nsu.fit.apotapova.snake.utils.Settings;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeMenuView;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeSettingsView;

/**
 * Snake Settings Controller.
 */
public class SnakeSettingsController extends SnakeSettingsView {

  public void saveBtn(ActionEvent actionEvent) {
    Settings.saveProperties();
  }

  public void closeBtn(ActionEvent actionEvent) {
    mainController.selectScene(SnakeMenuView.class);
  }

  /**
   * Selects level.
   *
   * @param actionEvent action event.
   */
  public void selectLevel(ActionEvent actionEvent) {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(new Stage());
    Settings.LEVEL_PATH = file.getName();
    Settings.addProperty("LEVEL_PATH", Settings.LEVEL_PATH);
    filename.setText(file.getName());
  }

  /**
   * Initializes settings listeners.
   */
  @FXML
  public void initialize() {
    super.initialize();
    setVisible(false);
    foodNumberSlider.valueProperty()
        .addListener((ObservableValue<? extends Number> num, Number old, Number newVal) -> {
          Settings.FOOD_NUMBER = (int) foodNumberSlider.getValue();
          Settings.addProperty("FOOD_NUMBER", Settings.FOOD_NUMBER);
        });
    speedSlider.valueProperty()
        .addListener((ObservableValue<? extends Number> num, Number old, Number newVal) -> {
          Settings.SPEED = Configuration.MAX_SPEED - (int) speedSlider.getValue();
          Settings.addProperty("SPEED", Settings.SPEED);
        });
    winSlider.valueProperty()
        .addListener((ObservableValue<? extends Number> num, Number old, Number newVal) -> {
          Settings.WIN_SNAKE_LENGTH = (int) winSlider.getValue();
          Settings.addProperty("WIN_SNAKE_LENGTH", Settings.WIN_SNAKE_LENGTH);
        });
  }
}
