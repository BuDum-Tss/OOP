package ru.nsu.fit.apotapova.snake.model.playingarea;

import java.util.List;
import javafx.geometry.Point2D;

public abstract class Map {

  protected final int length;
  protected final int width;
  protected List<List<Integer>> map;
  protected List<Point2D> spawnPoints;

  public Map(int length, int width) {
    this.length = length;
    this.width = width;
  }

  public int getLength() {
    return length;
  }

  public int getWidth() {
    return width;
  }

  public List<List<Integer>> getMap() {
    return map;
  }

  public List<Point2D> getSpawnPoints() {
    return spawnPoints;
  }

}
