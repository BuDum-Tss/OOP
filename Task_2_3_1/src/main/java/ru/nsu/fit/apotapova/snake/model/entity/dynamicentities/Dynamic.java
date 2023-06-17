package ru.nsu.fit.apotapova.snake.model.entity.dynamicentities;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.util.Pair;

/**
 * Dynamic entity interface.
 */
public interface Dynamic {

  void update();

  List<Pair<Point2D, Integer>> getChanges();
}
