package ru.nsu.fit.apotapova.snake.view;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Contains ImageView
 */
public class SnakeView extends EntityView {

  private final List<Point2D> links;
  Color color;

  public SnakeView(Color color,List<Point2D> links){
    this.color=color;
    this.links=links;
  }
  @Override
  public void updateView() {
    links.get(0);
  }
}
