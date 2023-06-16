package ru.nsu.fit.apotapova.snake.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.stream.Stream;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.data.GameState;
import ru.nsu.fit.apotapova.snake.model.entity.Entity;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Dynamic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.apotapova.snake.utils.Settings;

public class Game extends Thread {

  static final Logger rootLogger = LogManager.getRootLogger();
  private final PropertyChangeSupport viewSender = new PropertyChangeSupport(this);

  @Override
  public void run() {
    //viewSender
    rootLogger.info("Game is running");
    while (!(Thread.currentThread().isInterrupted()
        && GameData.getGameData().getGameState() != GameState.IN_PROGRESS)) {
      rootLogger.info("Next step");
      Stream<Entity> dynamicEntities = GameData.getGameData().getEntities().stream()
          .filter(entity -> entity instanceof Dynamic);
      dynamicEntities.map(entity -> (Dynamic) entity).forEach(entity -> {
        entity.update();
        entity.getChanges()
            .forEach(pointAndId -> viewSender.firePropertyChange("repaint tile", null, pointAndId));
      });
      synchronized (this) {
        try {
          if (GameData.getGameData().getGameState() == GameState.IN_PROGRESS) {
            Thread.currentThread().wait(Settings.SPEED);
          } else {
            Thread.currentThread().wait();
          }
        } catch (InterruptedException e) {
          if (GameData.getGameData().getGameState() == GameState.IN_PROGRESS) {
            Thread.currentThread().notify();
          }
        }
      }
    }
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    viewSender.addPropertyChangeListener(pcl);
  }
}
