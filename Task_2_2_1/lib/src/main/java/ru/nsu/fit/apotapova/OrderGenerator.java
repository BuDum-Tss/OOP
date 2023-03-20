package ru.nsu.fit.apotapova;

import java.util.Random;
import ru.nsu.fit.apotapova.order.Order;

public class OrderGenerator implements Runnable {

  private static int MAX_DISTANCE = 10;
  private static int FREQUENCY_OF_REQUESTS = 60;
  Pizzeria pizzeria;
  int orderNumber;
  Random random;

  public OrderGenerator(Pizzeria pizzeria) {
    this.pizzeria = pizzeria;
    orderNumber = 0;
    random = new Random();
  }


  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {

        Thread.sleep(random.nextInt(FREQUENCY_OF_REQUESTS) * 100L);

        pizzeria.addOrder(new Order(orderNumber++, random.nextInt(MAX_DISTANCE)));
      }
    } catch (InterruptedException ignored) {
    }
  }
}
