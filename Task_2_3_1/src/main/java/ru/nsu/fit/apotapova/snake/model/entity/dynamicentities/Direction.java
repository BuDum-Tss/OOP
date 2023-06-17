package ru.nsu.fit.apotapova.snake.model.entity.dynamicentities;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.geometry.Point2D;

/**
 * Direction of movement.
 */
public enum Direction {
  UP(new Point2D(0, -1)),
  DOWN(new Point2D(0, 1)),
  LEFT(new Point2D(-1, 0)),
  RIGHT(new Point2D(1, 0));
  private static final Map<Point2D, Direction> map = Arrays.stream(values())
      .collect(
          Collectors.toMap(direction -> direction.direction, direction -> direction));

  public static Direction getByVector(Point2D vector) {
    return map.get(vector);
  }

  public Point2D getDirection() {
    return direction;
  }

  private final Point2D direction;

  Direction(Point2D direction) {
    this.direction = direction;
  }

}