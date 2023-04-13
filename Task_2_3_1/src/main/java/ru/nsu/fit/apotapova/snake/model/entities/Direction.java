package ru.nsu.fit.apotapova.snake.model.entities;

import javafx.geometry.Point2D;

public enum Direction {
  UP(new Point2D(0, -1)),
  DOWN(new Point2D(0, 1)),
  LEFT(new Point2D(-1, 0)),
  RiGHT(new Point2D(1, 0));

  public Point2D getDirection() {
    return direction;
  }

  private final Point2D direction;

  Direction(Point2D direction) {
    this.direction = direction;
  }

}
