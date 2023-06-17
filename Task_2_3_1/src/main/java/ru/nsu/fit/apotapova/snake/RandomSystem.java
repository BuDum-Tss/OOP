package ru.nsu.fit.apotapova.snake;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.util.Pair;

public class RandomSystem {

  private static RandomSystem RANDOM_SYSTEM;
  private final Random random;

  private RandomSystem() {
    random = new Random();
  }

  public static RandomSystem getRandomSystem() {
    if (RANDOM_SYSTEM == null) {
      RANDOM_SYSTEM = new RandomSystem();
    }
    return RANDOM_SYSTEM;
  }

  public int getIntInRange(Pair<Integer, Integer> range) {
    return getIntInRange(range.getKey(), range.getValue());
  }

  public int getIntInRange(Integer from, Integer to) {
    return from + random.nextInt(to - from);
  }

  public Point2D getPosition(double mapWidth, double mapLength) {
    return new Point2D(getIntInRange(0, (int) mapWidth), getIntInRange(0, (int) mapLength));
  }
}
