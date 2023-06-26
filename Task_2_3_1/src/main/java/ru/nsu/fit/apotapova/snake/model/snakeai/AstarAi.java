package ru.nsu.fit.apotapova.snake.model.snakeai;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Stream;
import javafx.geometry.Point2D;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.entity.EntityType;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Direction;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Snake;
import ru.nsu.fit.apotapova.snake.view.tile.TileType;

/**
 * AI for snake using A* algorithm.
 */
public class AstarAi implements SnakeAi {

  private final Snake snake;
  private List<List<Integer>> map;
  private PriorityQueue<WayPoint> open;
  private PriorityQueue<WayPoint> closed;
  private Stack<Point2D> path;
  private List<Point2D> foodList;

  /**
   * Constructor.
   *
   * @param snake controlled snake
   */
  public AstarAi(Snake snake) {
    this.snake = snake;
    map = GameData.getGameData().getMap().stream()
        .map(list -> list.stream().map(tile -> getWeightByTileType(tile.getType())).toList())
        .toList();
    open = new PriorityQueue<>();
    closed = new PriorityQueue<>();
    path = new Stack<>();
  }

  @Override
  public void chooseDirection() {
    if (GameData.getGameData().getFoodNumber() == 0) {
      return;
    }
    if (path.size() == 0 || pathPointsChanged()) {
      updateMap();
      foodList = GameData.getGameData().getFood();
      open.clear();
      closed.clear();
      findPath();
      path.clear();
      recoverPath();
    }
    if (path.peek() != null) {
      setDirectionByPoint(path.pop());
    }
  }

  private void recoverPath() {
    Point2D target = foodList.stream().filter(point2D -> open.stream()
        .anyMatch(waypoint -> waypoint.getPoint().equals(point2D))).findFirst().orElseThrow();
    path.add(target);
    WayPoint current = getPreviousWaypointFromPath(target);
    path.add(current.getPoint());
    while (current.getPoint() != snake.getPosition().get(0)) {
      current = getPreviousWaypointFromPath(current.getPoint());
      path.add(current.getPoint());
    }
    path.pop();
    System.out.println("PATH  " + path);
  }

  private WayPoint getPreviousWaypointFromPath(Point2D point) {
    List<WayPoint> list = new java.util.ArrayList<>(
        Stream.of(Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT)
            .map(direction -> getWaypoint(Snake.nextPosition(point, direction), closed)).toList());
    while (list.contains(null)) {
      list.remove(null);
    }
    list.sort(Comparator.comparing(WayPoint::getWeight));
    return list.get(0);
  }

  private WayPoint getWaypoint(Point2D point, Collection<WayPoint> collection) {
    return collection.stream().filter(waypoint -> waypoint.getPoint().equals(point)).findFirst()
        .orElse(null);
  }

  private void findPath() {
    open.add(new WayPoint(snake.getPosition().get(0), 0));
    while (!foodInOpen()) {
      WayPoint processed = open.poll();
      List<WayPoint> list = Stream.of(Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT)
          .map(direction -> {
            Point2D newPoint = Snake.nextPosition(processed.getPoint(), direction);
            Integer weight = processed.getWeight() + map.get((int) newPoint.getX())
                .get((int) newPoint.getY()) + estimated_distance(newPoint);
            return new WayPoint(newPoint, weight);
          }).toList();
      list.forEach(waypoint -> {
        if (closed.contains(waypoint)) {
          return;
        }
        if (open.contains(waypoint)
            && getWaypoint(waypoint.getPoint(), open).getWeight() > waypoint.getWeight()) {
          open.remove(getWaypoint(waypoint.getPoint(), open));
        } else if (open.contains(waypoint)) {
          return;
        }
        open.add(waypoint);
      });
      closed.add(processed);
    }
  }

  private Integer estimated_distance(Point2D point) {
    List<Integer> list = new java.util.ArrayList<>(foodList.stream().map(
        target -> (int) (Math.abs(point.getX() - target.getX()) + Math.abs(
            point.getY() - target.getY()))).toList());
    Collections.sort(list);
    return list.get(0);
  }

  private void setDirectionByPoint(Point2D point) {
    Point2D vector = snake.getPosition().get(0).multiply(-1).add(point);
    if (vector.magnitude() > 1) {
      vector = vector.multiply(-1).normalize();
    }
    System.out.println(vector);
    Direction direction = Direction.getByVector(vector);
    if (direction == null) {
      return;
    }
    snake.setDirection(direction);
  }


  private static Integer getWeightByTileType(TileType type) {
    return switch (type) {
      case EMPTY -> 1;
      case FOOD -> 0;
      case WALL, SNAKE -> 1000;
    };
  }

  private void updateMap() {
    map = GameData.getGameData().getMap().stream()
        .map(list -> list.stream().map(tile -> getWeightByTileType(tile.getType())).toList())
        .toList();
  }

  private boolean pathPointsChanged() {
    List<Point2D> pathList = path.stream().toList();
    return pathList.stream().anyMatch(point -> !Objects.equals(
        map.get((int) point.getX()).get((int) point.getY()),
        getWeightByTileType(GameData.getGameData().getTileFromPosition(point).getType())));
  }

  private boolean foodInOpen() {
    return open.stream().anyMatch(
        waypoint -> GameData.getGameData().getTileFromPosition(waypoint.getPoint()).getEntityType()
            == EntityType.FOOD);
  }
}
