package ru.nsu.fit.apotapova.snake;

import java.util.Random;

public class RandomSystem {
  private Random random;
  public RandomSystem(int seed){
    this.random=new Random(seed);
  }

  public RandomSystem() {
    this.random=new Random();
  }

  public boolean isHappened(int probability){
    return random.nextInt(100)<probability;
  }
}
