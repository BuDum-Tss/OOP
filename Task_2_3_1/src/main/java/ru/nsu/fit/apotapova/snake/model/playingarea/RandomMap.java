package ru.nsu.fit.apotapova.snake.model.playingarea;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Point2D;
import ru.nsu.fit.apotapova.snake.RandomSystem;
import ru.nsu.fit.apotapova.snake.Utils;
import ru.nsu.fit.apotapova.snake.view.tile.TileType;

public class RandomMap extends Map {

  private RandomSystem randomSystem;
  private final int wallsProbability;
  private final int spawnPointProbability;


  @Override
  public String toString() {
    String str = "RandomMap{\n[\n";
    for (int x = 0; x < getLength(); x++) {
      for (int y = 0; y < getLength(); y++) {
        str = str.concat(map.get(x).get(y).toString() + ", ");
      }
      str = str.concat("\n");
    }
    str = str.concat("]}");
    return str;
  }

  public RandomMap(int length, int width, int seed, int wallsProbability, int spawnPointProbability) {
    super(length, width);
    randomSystem = new RandomSystem(seed);
    this.wallsProbability = wallsProbability;
    this.spawnPointProbability = spawnPointProbability;
    generateEmptyMap();
    addWallsToMap();
    addSpawnPoints();
    if (!checkAll()) throw new IllegalArgumentException("Generated map is wrong");
  }

  private void addSpawnPoints() {
    spawnPoints = new ArrayList<>();
    for (int x = 0; x < getLength(); x++) {
      for (int y = 0; y < getWidth(); y++) {
        if (randomSystem.isHappened(spawnPointProbability)) {
          Point2D currPoint = new Point2D(x, y);
          if (checkNeighbours(TileType.EMPTY.getId(), currPoint)) {
            spawnPoints.add(currPoint);
          }
        }
      }
    }
  }

  private void generateEmptyMap() {
    map = new ArrayList<>(getLength());
    for (int x = 0; x < getLength(); x++) {
      map.add(x, new ArrayList<>());
      for (int y = 0; y < getWidth(); y++) {
        map.get(x).add(TileType.EMPTY.getId());
      }
    }
  }

  private void addWallsToMap() {
    for (int x = 0; x < getLength(); x++) {
      for (int y = 0; y < getWidth(); y++) {
        if (randomSystem.isHappened(wallsProbability)) {
          if (checkNeighbours(TileType.EMPTY.getId(), new Point2D(x, y))) {
            map.get(x).remove(y);
            map.get(x).add(y, TileType.WALL.getId());
          }
        }
      }
    }
  }

  private boolean checkAll() {
    for (int x = 0; x < getLength(); x++) {
      for (int y = 0; y < getLength(); y++) {
        if (Objects.equals(map.get(x).get(y), TileType.EMPTY.getId())
            && Utils.getNeighbors(new Point2D(x, y), map).stream().filter(value -> Objects.equals(
            value, TileType.EMPTY.getId())).count() <= 1) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean checkNeighbours(Integer id, Point2D point) {
    List<Integer> numbers = Utils.getNeighborsWithPosition(point, map).stream().map(pair -> {
      if (pair.getValue() != null) {
        return (int) Utils.getNeighbors(pair.getKey(), map).stream()
            .filter(value -> value != null && ((int) value == id)).count();
      }
      return null;
    }).toList();
    return numbers.stream().filter(Objects::nonNull).noneMatch(value -> value <= 2);
  }
}
