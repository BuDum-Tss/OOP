package ru.nsu.fit.apotapova.snake.model.snakeai;

import java.util.Objects;
import javafx.geometry.Point2D;
import ru.nsu.fit.apotapova.snake.view.tile.TileType;

/**
 * Point for A* algorithm.
 */
public class WayPoint implements Comparable {

  private final Point2D point2D;
  private final Integer weight;

  public WayPoint(Point2D point2D, Integer weight) {
    this.point2D = point2D;
    this.weight = weight;
  }

  public Integer getWeight() {
    return weight;
  }

  public Point2D getPoint() {
    return point2D;
  }

  @Override
  public int compareTo(Object o) {
    return weight - ((WayPoint) o).weight;
  }


  @Override
  public String toString() {
    return "WayPoint{" + point2D + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WayPoint point = (WayPoint) o;
    return Objects.equals(point2D, point.point2D);
  }

  @Override
  public int hashCode() {
    return Objects.hash(point2D);
  }
}
