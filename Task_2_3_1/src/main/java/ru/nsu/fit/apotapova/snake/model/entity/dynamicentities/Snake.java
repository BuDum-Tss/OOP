package ru.nsu.fit.apotapova.snake.model.entity.dynamicentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.entity.Entity;
import ru.nsu.fit.apotapova.snake.model.entity.EntityType;

public class Snake extends Entity implements Dynamic {

  private Direction direction;
  private final LinkedList<Point2D> segments;
  private int growNumber = 0;
  private final String color;
  private final List<Pair<Point2D, Integer>> changes;

  public Snake(int id, LinkedList<Point2D> snake, String color) {
    super(id, EntityType.SNAKE);
    segments = snake;
    direction = Direction.getByVector(segments.getFirst().add(segments.get(1).multiply(-1)));
    if (color.length() != 6) {
      throw new IllegalArgumentException("Illegal color");
    }
    this.color = color;
    changes = new ArrayList<>();
  }

  public void setDirection(Direction direction) {
    entityLogger.info("Direction: " + this.direction + " changed: " + direction);
    this.direction = direction;
  }

  @Override
  public void interactWith(EntityType type) {
    switch (type) {
      case SNAKE -> {
        GameData.getGameData().removeFromGame(this);
        changes.clear();
        entityLogger.info("Snake removed from game");
      }
      case FOOD -> {
        grow(1);
        entityLogger.info("Snake grows");
      }
    }
  }

  @Override
  public List<Point2D> getPosition() {
    return segments;
  }

  private void grow(int number) {
    growNumber = number;
  }

  private void move() {
    changes.add(new Pair<>(segments.getFirst(), -id));
    segments.addFirst(nextPosition());
    changes.add(new Pair<>(segments.getFirst(), id));
    if (growNumber == 0) {
      changes.add(new Pair<>(segments.getLast(), 0));
      segments.removeLast();
    } else {
      growNumber--;
    }
  }

  private Point2D nextPosition() {
    Point2D notCheckedPosition = segments.getFirst().add(direction.getDirection());
    int x = (int) (Math.abs(notCheckedPosition.getX() + GameData.getGameData().getMapWidth())
        % GameData.getGameData().getMapWidth());
    int y = (int) (Math.abs(notCheckedPosition.getY()  + GameData.getGameData().getMapLength())
        % GameData.getGameData().getMapLength());
    return new Point2D(x, y);
  }

  @Override
  public void update() {
    changes.clear();
    Point2D next = nextPosition();
    move();
    Integer id = GameData.getGameData().getTileFromPosition(next).getId();
    EntityType interactingEntityType = GameData.getGameData().getTileFromPosition(next)
        .getEntityType();
    if (interactingEntityType != null) {
      GameData.getGameData().getEntityById(id);
      interactWith(interactingEntityType);
      Entity interactedEntity = GameData.getGameData()
          .getEntityById(GameData.getGameData().getTileFromPosition(next).getId());
      if (interactedEntity != null) {
        interactedEntity.interactWith(this.getType());
      }
    }
  }

  @Override
  public List<Pair<Point2D, Integer>> getChanges() {
    return changes;
  }

  public String getColor() {
    return color;
  }

  public int getLength() {
    return segments.size();
  }
}
