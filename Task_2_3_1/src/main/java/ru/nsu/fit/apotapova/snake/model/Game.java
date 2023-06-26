package ru.nsu.fit.apotapova.snake.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.stream.Stream;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.apotapova.snake.RandomSystem;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.data.GameResult;
import ru.nsu.fit.apotapova.snake.model.data.GameState;
import ru.nsu.fit.apotapova.snake.model.entity.Entity;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Dynamic;
import ru.nsu.fit.apotapova.snake.model.entity.staticentities.Food;
import ru.nsu.fit.apotapova.snake.model.snakeai.SnakeAi;
import ru.nsu.fit.apotapova.snake.utils.Settings;
import ru.nsu.fit.apotapova.snake.view.tile.TileType;

/**
 * Main game thread.
 */
public class Game extends Thread {

  static final Logger rootLogger = LogManager.getRootLogger();
  private final PropertyChangeSupport viewSender = new PropertyChangeSupport(this);

  @Override
  public void run() {
    //viewSender
    rootLogger.info("Thread is running");
    while (!(GameData.getGameData().getGameState() == GameState.CLOSED)) {
      rootLogger.info("Next step");
      GameData.getGameData().getAi().forEach(SnakeAi::chooseDirection);
      Stream<Entity> dynamicEntities = GameData.getGameData().getEntities().stream()
          .filter(entity -> entity instanceof Dynamic);
      dynamicEntities.map(entity -> (Dynamic) entity).forEach(entity -> {
        entity.update();
        entity.getChanges()
            .forEach(pointAndId -> viewSender.firePropertyChange("repaint tile", null, pointAndId));
      });
      addMissingFood();
      if (GameData.getGameData().getGameResult() == GameResult.VICTORY) {
        viewSender.firePropertyChange("victory", null, null);
        rootLogger.info("Victory");
      } else if (GameData.getGameData().getGameResult() == GameResult.LOSS) {
        viewSender.firePropertyChange("loss", null, null);
        rootLogger.info("Loss");
      }
      synchronized (this) {
        try {
          if (GameData.getGameData().getGameState() == GameState.IN_PROGRESS) {
            Thread.currentThread().wait(Settings.SPEED);
          } else {
            Thread.currentThread().wait();
          }
        } catch (InterruptedException e) {
          Thread.currentThread().notify();
        }
      }
    }
    rootLogger.info("Thread closed");
  }

  private void addMissingFood() {
    while (GameData.getGameData().getFoodNumber() < Settings.FOOD_NUMBER
        && GameData.getGameData().getTilesNumber(0) > 0) {
      int id = getFreeId();
      Point2D position = getEmptyPosition();
      GameData.getGameData().addToGame(new Food(id, position));
      viewSender.firePropertyChange("repaint tile", null, new Pair<>(position, id));
    }
  }

  private static Point2D getEmptyPosition() {
    Point2D position = RandomSystem.getRandomSystem()
        .getPosition(GameData.getGameData().getMapWidth(), GameData.getGameData().getMapLength());
    while (GameData.getGameData().getTileFromPosition(position).getId() != 0) {
      position = RandomSystem.getRandomSystem()
          .getPosition(GameData.getGameData().getMapWidth(), GameData.getGameData().getMapLength());
    }
    return position;
  }

  private static int getFreeId() {
    int id = RandomSystem.getRandomSystem().getIntInRange(TileType.FOOD.getIdRange());
    while (GameData.getGameData().getEntityById(id) != null) {
      id = RandomSystem.getRandomSystem().getIntInRange(TileType.FOOD.getIdRange());
    }
    return id;
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    viewSender.addPropertyChangeListener(pcl);
  }
}
