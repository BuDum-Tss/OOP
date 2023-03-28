package ru.nsu.fit.apotapova;

import java.util.Random;
import ru.nsu.fit.apotapova.order.Order;

/**
 * Class for simulating the receipt of orders in a pizzeria.
 */
public class OrderGenerator implements Runnable {

  private final Pizzeria pizzeria;
  private final Random random;
  private int maxDistance = 100;
  private int frequencyOfRequests = 10000;
  private final NotificationSystem notificationSystem;

  /**
   * Constructor.
   *
   * @param pizzeria tested pizzeria
   */
  public OrderGenerator(Pizzeria pizzeria, NotificationSystem notificationSystem) {
    this.pizzeria = pizzeria;
    this.notificationSystem = notificationSystem;
    random = new Random();
  }

  /**
   * Sets order settings.
   *
   * @param maxDistance         maximum distance of delivering(m)
   * @param frequencyOfRequests frequency of requests(milliseconds)
   */
  public void setNewSettings(int maxDistance, int frequencyOfRequests) {
    this.maxDistance = maxDistance;
    this.frequencyOfRequests = frequencyOfRequests;
  }

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        Thread.sleep(random.nextInt(frequencyOfRequests));
        Order newOrder = new Order(random.nextInt(maxDistance));
        newOrder.setNotificationSystem(notificationSystem);
        pizzeria.addOrder(newOrder);
      }
    } catch (InterruptedException ignored) {
    }
  }
}
