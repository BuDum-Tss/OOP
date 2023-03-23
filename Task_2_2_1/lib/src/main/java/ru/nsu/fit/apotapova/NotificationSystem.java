package ru.nsu.fit.apotapova;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ru.nsu.fit.apotapova.order.Order;
import ru.nsu.fit.apotapova.order.Order.OrderStatusMod;

/**
 * Class for notification of the status of orders.
 */
public class NotificationSystem implements Runnable {

  protected final HashMap<Integer, Order> trackedOrders;

  public NotificationSystem() {
    trackedOrders = new HashMap<>();
  }

  /**
   * Add order to HasMap for tracking.
   *
   * @param newOrder added Order
   */
  public void track(Order newOrder) {
    synchronized (trackedOrders) {
      trackedOrders.put(newOrder.getNumber(), newOrder);
    }
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      List<Integer> keyList;
      synchronized (trackedOrders) {
        keyList = new ArrayList<>(trackedOrders.keySet());
      }
      keyList.forEach(key -> {
        Order order = trackedOrders.get(key);
        String status = order.manageStatus(OrderStatusMod.GET);
        if (status != null) {
          System.out.println("[" + order.getNumber() + "]: " + order.getStatus().getMessage());
        }
      });
    }
  }
}
