package ru.nsu.fit.apotapova.snake.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.entity.EntityType;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Direction;
import ru.nsu.fit.apotapova.snake.view.tile.Tile;

/**
 * Map Parser.
 */
public class MapParser {

  /**
   * Finds snakes at map.
   *
   * @param map map
   * @return map with id and snake segments
   */
  public static Map<Integer, LinkedList<Point2D>> getSnakesPositionsFromMap(List<List<Tile>> map) {
    HashMap<Integer, LinkedList<Point2D>> snakesHashMap = new HashMap<>();
    Pair<List<Integer>, List<Point2D>> snakeHeadsData = findHeads(map);

    for (int i = 0; i < snakeHeadsData.getKey().size(); i++) {
      int id = snakeHeadsData.getKey().get(i);
      Point2D head = snakeHeadsData.getValue().get(i);
      snakesHashMap.put(id, findBody(head, map));
    }
    return snakesHashMap;
  }

  private static Pair<List<Integer>, List<Point2D>> findHeads(List<List<Tile>> map) {
    List<Integer> snakesIds = new ArrayList<>();
    List<Point2D> snakesPositions = new ArrayList<>();
    for (int x = 0; x < map.size(); x++) {
      for (int y = 0; y < map.get(0).size(); y++) {
        if (map.get(x).get(y).getEntityType() == EntityType.SNAKE
            && map.get(x).get(y).getId() > 0) {
          snakesIds.add(map.get(x).get(y).getId());
          snakesPositions.add(new Point2D(x, y));
        }
      }
    }
    return new Pair<>(snakesIds, snakesPositions);
  }

  private static LinkedList<Point2D> findBody(Point2D headPosition, List<List<Tile>> map) {
    LinkedList<Point2D> segments = new LinkedList<>();
    segments.add(headPosition);
    Point2D currSegment = headPosition;
    List<Point2D> neighbors = getNeighbors(currSegment, map);
    while (neighbors.size() > 1 || currSegment == headPosition) {
      int i = 0;
      while (segments.contains(neighbors.get(i))
          && GameData.getGameData().getTileFromPosition(neighbors.get(i)).getEntityType()
          == EntityType.SNAKE) {
        i++;
      }
      segments.add(neighbors.get(i));
      currSegment = neighbors.get(i);
      neighbors = getNeighbors(currSegment, map);
    }
    return segments;
  }

  private static List<Point2D> getNeighbors(Point2D currSegment, List<List<Tile>> map) {
    List<Point2D> neighbors = new ArrayList<>();
    List.of(Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT).forEach(direction -> {
      Point2D point = getPosition(currSegment, direction);
      if (map.get((int) point.getX()).get((int) point.getY()).getEntityType() == EntityType.SNAKE) {
        neighbors.add(point);
      }
    });
    return neighbors;
  }

  private static Point2D getPosition(Point2D currSegment, Direction direction) {
    Point2D notCheckedPosition = currSegment.add(direction.getDirection());
    int x = (int) (Math.abs(notCheckedPosition.getX()) % GameData.getGameData().getMapLength());
    int y = (int) (Math.abs(notCheckedPosition.getY()) % GameData.getGameData().getMapWidth());
    return new Point2D(x, y);
  }
}
