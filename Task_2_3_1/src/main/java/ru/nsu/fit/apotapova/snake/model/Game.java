package ru.nsu.fit.apotapova.snake.model;

import java.util.List;
import java.util.Map;
import ru.nsu.fit.apotapova.snake.data.Configuration;
import ru.nsu.fit.apotapova.snake.data.Settings;
import ru.nsu.fit.apotapova.snake.model.entities.Snake;

public class Game extends Thread {
  List<Snake> snakes;
  public Game(List<Snake> snakes){
    this.snakes=snakes;
  }

  @Override
  public void run(){
    while (!Thread.currentThread().isInterrupted()){
      snakes.forEach(Snake::move);
      try {
        sleep(Settings.SPEED);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
