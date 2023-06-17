package ru.nsu.fit.apotapova.snake.model.entity.staticentities;

import java.util.List;
import javafx.geometry.Point2D;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.entity.Entity;
import ru.nsu.fit.apotapova.snake.model.entity.EntityType;

public class Food extends Entity implements Static {

  private final Point2D position;

  public Food(int id, Point2D position) {
    super(id, EntityType.FOOD);
    this.position = position;
  }

  @Override
  public List<Point2D> getPosition() {
    return List.of(position);
  }

  @Override
  public void interactWith(EntityType type) {
    switch (type) {
      case SNAKE -> {
        GameData.getGameData().removeFromGame(this);
      }
      default -> throw new IllegalArgumentException("Unexpected EntityType: " + type);
    }
  }
}
