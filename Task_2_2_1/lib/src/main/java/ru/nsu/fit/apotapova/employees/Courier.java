package ru.nsu.fit.apotapova.employees;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.apotapova.employees.roles.Consumer;
import ru.nsu.fit.apotapova.json.CourierData;
import ru.nsu.fit.apotapova.order.Order;
import ru.nsu.fit.apotapova.order.OrderStatus;

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
    fillTrunk();
    deliverOrders();
  }

  private void fillTrunk() {
    while (!((pendingOrders.isEmpty() && trunk.size() != 0)
        || trunk.size() == trunkCapacity || Thread.currentThread().isInterrupted())) {
      Order currOrder = takeOrder();
      if (currOrder == null) {
        break;
      }
      if (currOrder.getStatus() == OrderStatus.SPECIAL) {
        trunk.add(currOrder);
        break;
      }
      currOrder.changeStatus();
      trunk.add(currOrder);
    }
  }

  private void deliverOrders() {
    while (!trunk.isEmpty()) {
      Order currOrder = trunk.get(0);
      if (currOrder.getStatus() == OrderStatus.SPECIAL) {
        trunk.remove(currOrder);
        Thread.currentThread().interrupt();
        break;
      }
      currOrder.changeStatus();
      deliver(currOrder);
      currOrder.changeStatus();
      trunk.remove(currOrder);
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
    Order takenOrder;
    if (trunk.isEmpty()) {
      try {
        takenOrder = pendingOrders.take();
      } catch (InterruptedException e) {
        throw new RuntimeException(
            "Unexpected interruption while taking the order at:" + this.getClass().toString());
      }
    } else {
      takenOrder = pendingOrders.poll();
    }
    return takenOrder;
  }
}
