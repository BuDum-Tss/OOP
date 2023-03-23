package ru.nsu.fit.apotapova.employees;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.apotapova.employees.roles.Consumer;
import ru.nsu.fit.apotapova.json.CourierData;
import ru.nsu.fit.apotapova.order.Order;
import ru.nsu.fit.apotapova.order.Order.OrderStatusMod;

/**
 * The courier class receives the order from the warehouse and delivers the order.
 */
public class Courier extends OrderExecutor implements Consumer {

  private final List<Order> trunk;
  private final int trunkCapacity;
  private final long speed;
  private final BlockingQueue<Order> pendingOrders;

  /**
   * Constructor.
   *
   * @param courierData   data with trunk capacity ,courier speed
   * @param pendingOrders the queue with pending orders at storage
   */
  public Courier(CourierData courierData, BlockingQueue<Order> pendingOrders) {
    this.trunkCapacity = courierData.getTrunkCapacity();
    this.trunk = new ArrayList<>(trunkCapacity);
    this.speed = courierData.getSpeed();
    this.pendingOrders = pendingOrders;
  }

  @Override
  protected void work() {
    while (!isInterrupted.get() && !((pendingOrders.isEmpty() && trunk.size() != 0)
        || trunk.size() == trunkCapacity)) {
      trunk.add(takeOrder());
    }
    while (!trunk.isEmpty()) {
      Order order = trunk.get(0);
      if (order == null) {
        break;
      }
      deliver(order);
      order.manageStatus(OrderStatusMod.CHANGE);
      trunk.remove(order);
    }
  }

  private void deliver(Order currentOrder) {
    synchronized (this) {
      try {
        Thread.sleep(currentOrder.getDistance() / speed);
      } catch (InterruptedException e) {
        throw new RuntimeException(
            "Unexpected interruption while sleeping at: " + this.getClass().toString());
      }
    }
  }

  @Override
  public Order takeOrder() {
    Order takenOrder = null;
    try {
      if (trunk.isEmpty()) {
        synchronized (this) {
          isWaitingOrder.set(true);
          takenOrder = pendingOrders.take();
          isWaitingOrder.set(false);
        }
      } else {
        takenOrder = pendingOrders.poll();
      }
      if (takenOrder != null) {
        takenOrder.manageStatus(OrderStatusMod.CHANGE);
      }
    } catch (InterruptedException e) {
      if (!isInterrupted.get()) {
        throw new RuntimeException("Unexpected interruption while taking the order");
      }
    }
    return takenOrder;
  }
}
