package ru.nsu.fit.apotapova.snake.view.scene.sceneview;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import ru.nsu.fit.apotapova.snake.utils.Configuration;
import ru.nsu.fit.apotapova.snake.utils.Settings;
import ru.nsu.fit.apotapova.snake.view.scene.SceneView;

/**
 * Snake settings view.
 */
public abstract class SnakeSettingsView extends SceneView {

  public AnchorPane scene;
  public Slider foodNumberSlider;
  public Slider speedSlider;
  public Label filename;
  public Slider winSlider;

  /**
   * Initialization.
   */
  @FXML
  public void initialize() {
    filename.setText(Settings.LEVEL_PATH);

    foodNumberSlider.setMin(0);
    foodNumberSlider.setMax(Configuration.MAX_FOOD_NUMBER);
    foodNumberSlider.setValue(Settings.FOOD_NUMBER);
    foodNumberSlider.setShowTickLabels(true);
    foodNumberSlider.setMajorTickUnit(10);

    speedSlider.setMin(Configuration.MIN_SPEED);
    speedSlider.setMax(Configuration.MAX_SPEED);
    speedSlider.setValue(Configuration.MAX_SPEED - Settings.SPEED);
    speedSlider.setShowTickLabels(true);
    speedSlider.setMajorTickUnit(100);

    winSlider.setMin(3);
    winSlider.setMax(Configuration.MAX_WIN_SNAKE_LENGTH);
    winSlider.setValue(Settings.WIN_SNAKE_LENGTH);
    winSlider.setShowTickLabels(true);
    winSlider.setMajorTickUnit(10);
  }
}
