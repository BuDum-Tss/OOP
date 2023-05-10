package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import com.google.gson.Gson;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import ru.nsu.fit.apotapova.snake.controller.SceneController;
import ru.nsu.fit.apotapova.snake.data.Configuration;
import ru.nsu.fit.apotapova.snake.model.Game;
import ru.nsu.fit.apotapova.snake.model.entities.Direction;
import ru.nsu.fit.apotapova.snake.model.entities.Snake;
import ru.nsu.fit.apotapova.snake.model.playingarea.LoadedMap;
import ru.nsu.fit.apotapova.snake.model.playingarea.Map;
import ru.nsu.fit.apotapova.snake.view.MapView;

public class GameController extends SceneController {
  public AnchorPane scene;
  public GridPane gameArea;
  private MapView mapView;
  private Snake player;
  @FXML
  public void initialize(){

    scene.setOnKeyPressed(
        event -> {
          switch (event.getCode()) {
            case UP:
              player.setDirection(Direction.UP);
              break;
            case DOWN:
              player.setDirection(Direction.DOWN);
              break;
            case RIGHT:
              player.setDirection(Direction.RIGHT);
              break;
            case LEFT:
              player.setDirection(Direction.LEFT);
              break;
          }
        });
  }

  public void newGame() {
    gameArea.setGridLinesVisible(true);
    Map map = LoadedMap.MapLoader.load(Configuration.LEVELS_PATH+"level1.json");
    prepareGameArea(map.getLength(),map.getWidth());
    mapView =new MapView(map);
    mapView.addTilesTo(gameArea);
    player = new Snake(new Point2D(3,3), Color.AQUA);
    Game game = new Game(List.of(player));
    game.start();
  }

  private void prepareGameArea(int length, int width) {
    gameArea.getColumnConstraints().clear();
    gameArea.getRowConstraints().clear();
    for(int i=0;i<length;i++) gameArea.getRowConstraints().add(new RowConstraints());
    for(int i=0;i<width;i++) gameArea.getColumnConstraints().add(new ColumnConstraints());
  }
}
