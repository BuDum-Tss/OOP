package ru.nsu.fit.apotapova.snake.controller.scenecontrollers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.apotapova.snake.model.Game;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.data.GameState;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Direction;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Snake;
import ru.nsu.fit.apotapova.snake.model.gamerules.GameRule;
import ru.nsu.fit.apotapova.snake.model.level.MapLoader;
import ru.nsu.fit.apotapova.snake.utils.Configuration;
import ru.nsu.fit.apotapova.snake.utils.MapParser;
import ru.nsu.fit.apotapova.snake.utils.Settings;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeGameView;
import ru.nsu.fit.apotapova.snake.view.scene.sceneview.SnakeMenuView;
import ru.nsu.fit.apotapova.snake.view.tile.TileType;

/**
 * Game Controller.
 */
public class SnakeGameController extends SnakeGameView {

  protected static final Logger gameControllerLogger = LogManager.getLogger("GameController");
  private Snake player;

  /**
   * Controller initialisation.
   */
  @FXML
  public void initialize() {
    setVisible(false);
    scene.sceneProperty()
        .addListener((observable, oldScene, newScene) -> {
          if (newScene != null) {
            newScene.setOnKeyPressed(
                event -> {
                  switch (event.getCode()) {
                    case W -> player.setDirection(Direction.UP);
                    case A -> player.setDirection(Direction.LEFT);
                    case S -> player.setDirection(Direction.DOWN);
                    case D -> player.setDirection(Direction.RIGHT);
                    case ESCAPE -> {
                      if (GameData.getGameData().getGameState() == GameState.IN_PROGRESS) {
                        stopGame();
                        pauseMenu.setVisible(true);
                      } else {
                        continueGame();
                        pauseMenu.setVisible(false);
                      }
                    }
                  }
                });

          }
        });
  }

  /**
   * Prepares new game.
   */
  public void newGame() {
    gameControllerLogger.info("Preparing game...");
    GameData.getGameData()
        .setMap(MapLoader.loadMap(Configuration.LEVELS_PATH + Settings.LEVEL_PATH));
    addSnakes();
    setGameRules();
    game = new Game();
    game.setName("Game");
    game.addPropertyChangeListener(this);
    gameControllerLogger.info("New game prepared");
  }

  @Override
  protected void startGame() {
    GameData.getGameData().setGameState(GameState.IN_PROGRESS);
    game.start();
    gameControllerLogger.info("Game started");
  }

  @Override
  protected void stopGame() {
    GameData.getGameData().setGameState(GameState.PAUSE);
    gameControllerLogger.info("Game stopped");
  }

  @Override
  protected void closeGame() {
    GameData.getGameData().setGameState(GameState.CLOSED);
    game.interrupt();
    try {
      game.join();
    } catch (InterruptedException e) {
      throw new RuntimeException("Unexpected interruption while waiting thread Game");
    }
    GameData.getGameData().clearGameData();
  }

  public void continueGame() {
    GameData.getGameData().setGameState(GameState.IN_PROGRESS);
    game.interrupt();
    pauseMenu.setVisible(false);
    gameControllerLogger.info("Game continued");
  }

  public void quitGame(ActionEvent actionEvent) {
    closeGame();
    mainController.selectScene(SnakeMenuView.class);
    gameControllerLogger.info("Game closed");
  }

  private void addSnakes() {
    Map<Integer, LinkedList<Point2D>> snakesSegments = MapParser.getSnakesPositionsFromMap(
        GameData.getGameData().getMap());
    player = new Snake(Configuration.SNAKE_ID, snakesSegments.get(Configuration.SNAKE_ID),
        "00FF00");
    GameData.getGameData().addToGame(player);

    List<Snake> aiSnakes = new ArrayList<>();
    snakesSegments.keySet().stream()
        .filter(id -> TileType.getById(id) == TileType.SNAKE && id != Configuration.SNAKE_ID)
        .forEach(id -> aiSnakes.add(new Snake(id, snakesSegments.get(id), "0000FF")));

    aiSnakes.forEach(snake -> GameData.getGameData().addToGame(snake));
  }

  private void setGameRules() {
    GameData.getGameData().addRule(GameRule.VICTORY,
        () -> (GameData.getGameData().getEntityById(Configuration.SNAKE_ID) != null)
            && ((Snake) GameData.getGameData().getEntityById(Configuration.SNAKE_ID)).getLength()
            >= Settings.WIN_SNAKE_LENGTH);
    GameData.getGameData().addRule(GameRule.LOSS,
        () -> GameData.getGameData().getEntityById(Configuration.SNAKE_ID) == null);
  }

  public void retry(ActionEvent actionEvent) {
    closeGame();
    newGame();
    prepareScene();
    startGame();
  }
}