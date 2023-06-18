package ru.nsu.fit.apotapova.snake.model.entity.dynamicentities;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javafx.geometry.Point2D;
import javafx.util.Pair;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.entity.Entity;
import ru.nsu.fit.apotapova.snake.model.entity.EntityType;

/**
 * Entity Snake.
 */
public class Snake extends Entity implements Dynamic {

  private Direction direction;
  private final LinkedList<Point2D> segments;
  private int growNumber = 0;
  private final String color;
  private final Stack<Pair<Point2D, Integer>> changes;
  Queue<Direction> directionQueue;

  /**
   * Constructor.
   *
   * @param id       id
   * @param segments coordinates of snake segments
   * @param color    snake color
   */
  public Snake(int id, LinkedList<Point2D> segments, String color) {
    super(id, EntityType.SNAKE);
    this.segments = segments;
    direction = Direction.getByVector(
        this.segments.getFirst().add(this.segments.get(1).multiply(-1)));
    if (color.length() != 6) {
      throw new IllegalArgumentException("Illegal color");
    }
    this.color = color;
    changes = new Stack<>();
    directionQueue = new ArrayDeque<>();
  }

  public void setDirection(Direction direction) {
    entityLogger.info("Direction: " + this.direction + " changed: " + direction);
    this.direction = direction;
  }

  @Override
  public void interactWith(EntityType type) {
    switch (type) {
      case SNAKE, WALL -> {
        GameData.getGameData().removeFromGame(this);
        Stack<Pair<Point2D, Integer>> newChanges = getChangesTilesToEmpty();
        changes.clear();
        changes.addAll(newChanges);
        entityLogger.info("Snake removed from game");
      }
      case FOOD -> {
        grow(1);
        entityLogger.info("Snake grows");
      }
      default -> throw new IllegalArgumentException("Unexpected EntityType: " + type);
    }
  }

  private Stack<Pair<Point2D, Integer>> getChangesTilesToEmpty() {
    Stack<Pair<Point2D, Integer>> newChanges = new Stack<>();
    if (changes.peek().getValue() == 0) {
      newChanges.push(changes.pop());
    }
    segments.removeFirst();
    segments.forEach(segment -> newChanges.add(new Pair<>(segment, 0)));
    return newChanges;
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
    segments.addFirst(nextPosition(segments.getFirst(), direction));
    changes.add(new Pair<>(segments.getFirst(), id));
    if (growNumber == 0) {
      changes.add(new Pair<>(segments.getLast(), 0));
      segments.removeLast();
    } else {
      growNumber--;
    }
  }

  /**
   * Calculates next position.
   *
   * @param point current position
   * @param direction direction of movement
   * @return new position
   */
  public static Point2D nextPosition(Point2D point, Direction direction) {
    Point2D notCheckedPosition = point.add(direction.getDirection());
    int x = (int) (Math.abs(notCheckedPosition.getX() + GameData.getGameData().getMapWidth())
        % GameData.getGameData().getMapWidth());
    int y = (int) (Math.abs(notCheckedPosition.getY() + GameData.getGameData().getMapLength())
        % GameData.getGameData().getMapLength());
    return new Point2D(x, y);
  }

  @Override
  public void update() {
    if (directionQueue.peek() != null && !Direction.isOpposite(direction, directionQueue.peek())) {
      direction = directionQueue.poll();
    }
    changes.clear();
    Point2D next = nextPosition(segments.getFirst(), direction);
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

  public void addDirection(Direction direction) {
    directionQueue.add(direction);
  }
}
