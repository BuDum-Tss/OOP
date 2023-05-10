package ru.nsu.fit.apotapova.snake.model.entities;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import ru.nsu.fit.apotapova.snake.view.SnakeView;

public class Snake extends Entity implements Control, Dynamic {
  private final LinkedList<Point2D> snakeLinks;
  private volatile Direction direction;
  private final AtomicInteger newLinks;
  private final SnakeView view;

  public Snake(Point2D start, Color color){
    super(EntityType.SNAKE);
    snakeLinks = new LinkedList<>(List.of(start, start.add(new Point2D(0,1))));
    direction=Direction.UP;
    newLinks = new AtomicInteger(0);
    view = new SnakeView(color,snakeLinks);
  }
  public void grow(int number){
    newLinks.addAndGet(number);
  }
  @Override
  public void interactWith(Entity entity) {
    switch (entity.getType()){
      case SNAKE, WALL -> status = EntityStatus.DIED;
      case FOOD -> grow(1);
    }
  }
  @Override
  public void setDirection(Direction direction) {
    this.direction = direction;
  }
  @Override
  public void move
      (){
    snakeLinks.addFirst(snakeLinks.getFirst().add(direction.getDirection()));
    if (newLinks.get()==0) snakeLinks.removeLast();
    else newLinks.addAndGet(-1);
  }
}