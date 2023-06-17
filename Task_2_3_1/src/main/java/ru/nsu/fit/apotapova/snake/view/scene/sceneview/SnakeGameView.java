package ru.nsu.fit.apotapova.snake.view.scene.sceneview;

import java.beans.PropertyChangeEvent;
import java.util.List;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.apotapova.snake.model.Game;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.view.scene.SceneView;
import java.beans.PropertyChangeListener;
import ru.nsu.fit.apotapova.snake.view.tile.Tile;

public abstract class SnakeGameView extends SceneView implements PropertyChangeListener {

  protected static final Logger gameViewLogger = LogManager.getLogger("GameView");
  public AnchorPane scene;
  public GridPane gameArea;
  protected Game game;
  public AnchorPane endMenu;
  public AnchorPane pauseMenu;
  public Label label;

  @Override
  public void openScene() {
    gameViewLogger.info("GameScene opened");
    newGame();
    prepareScene();
    startGame();
  }

  protected void prepareScene() {
    prepareGameArea();
    addTilesToPane();
    endMenu.setVisible(false);
    pauseMenu.setVisible(false);
  }

  private void addTilesToPane() {
    List<List<Tile>> map = GameData.getGameData().getMap();
    for (int x = 0; x < GameData.getGameData().getMapWidth(); x++) {
      for (int y = 0; y < GameData.getGameData().getMapLength(); y++) {
        map.get(x).get(y).updateViewById();
        Rectangle tile = map.get(x).get(y).getView();
        gameArea.add(tile, x, y);
      }
    }
  }

  @Override
  public void closeScene() {
    gameViewLogger.info("GameScene closed");
  }

  protected abstract void newGame();

  protected abstract void startGame();

  protected abstract void stopGame();

  protected abstract void closeGame();

  private void prepareGameArea() {
    gameArea.setGridLinesVisible(true);
    gameArea.getColumnConstraints().clear();
    gameArea.getRowConstraints().clear();
    for (int i = 0; i < GameData.getGameData().getMapLength(); i++) {
      gameArea.getRowConstraints().add(new RowConstraints());
    }
    for (int i = 0; i < GameData.getGameData().getMapWidth(); i++) {
      gameArea.getColumnConstraints().add(new ColumnConstraints());
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "repaint tile" -> {
        Pair<Point2D, Integer> pointAndId = (Pair<Point2D, Integer>) evt.getNewValue();
        GameData.getGameData().getTileFromPosition(pointAndId.getKey())
            .setId(pointAndId.getValue());
        Platform.runLater(() -> GameData.getGameData().getTileFromPosition(pointAndId.getKey()).updateViewById());
      }
      case "victory" -> {
        Platform.runLater(() -> label.setText("Victory"));
        stopGame();
        endMenu.setVisible(true);
      }
      case "loss" -> {
        Platform.runLater(() -> label.setText("Loss"));
        stopGame();
        endMenu.setVisible(true);
      }
    }
  }
}
