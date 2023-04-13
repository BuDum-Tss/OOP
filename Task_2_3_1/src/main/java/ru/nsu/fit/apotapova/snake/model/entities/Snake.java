package ru.nsu.fit.apotapova.snake.model.entities;


import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Point2D;

public class Snake extends Entity {
  private final LinkedList<Point2D> snakeLinks;
  private Direction direction;

  public Snake(Point2D start){
    super(EntityType.SNAKE);
    snakeLinks = new LinkedList<>(List.of(start, start.add(new Point2D(0,1))));
    direction=Direction.UP;
  }
   private void move(){
    snakeLinks.addFirst(snakeLinks.getFirst().add(direction.getDirection()));
    snakeLinks.removeLast();
  }
  public void setDirection(Direction direction) {
    this.direction = direction;
  }
  public void grow(){
    snakeLinks.addFirst(snakeLinks.getFirst().add(direction.getDirection()));
  }
  @Override
  public void interactWith(Entity entity) {
    switch (entity.getType()){
      case SNAKE, WALL -> status = EntityStatus.DIED;
      case FOOD -> grow();
    }
  }
}