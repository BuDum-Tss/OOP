package ru.nsu.fit.apotapova.snake.view.scene;

import javafx.scene.layout.AnchorPane;

public abstract class SceneView {

  public AnchorPane scene;
  protected MainView mainController;

  public void setVisible(boolean value) {
    scene.setVisible(value);
    scene.setDisable(!value);
  }

  public void setMainController(MainView mainController) {
    this.mainController = mainController;
  }

  public boolean isVisible() {
    return scene.isVisible();
  }

}
