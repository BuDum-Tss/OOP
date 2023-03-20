package ru.nsu.fit.apotapova.employees;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.apotapova.employees.roles.Consumer;
import ru.nsu.fit.apotapova.json.CourierData;
import ru.nsu.fit.apotapova.order.Order;

public class Courier extends OrderExecutor implements Consumer {

  final List<Order> trunk;
  private final int trunkCapacity;
  private final long speed;
  private final BlockingQueue<Order> pendingOrders;

  public Courier(CourierData courierData, BlockingQueue<Order> pendingOrders) {
    this.trunkCapacity = courierData.getTrunkCapacity();
    trunk = new ArrayList<>(trunkCapacity);
    this.speed = courierData.getSpeed();
    this.pendingOrders = pendingOrders;
  }

  @Override
  protected void work() throws InterruptedException {
    while (!((pendingOrders.isEmpty() && trunk.size() != 0) || trunk.size() == trunkCapacity)) {
      trunk.add(takeOrder());
    }
    while (!trunk.isEmpty()) {
      Order order = trunk.get(0);
      if (order == null) {
        break;
      }
      deliver(order);
      order.changeStatus();
      trunk.remove(order);
    }
  }

  private void deliver(Order currentOrder) throws InterruptedException {
    synchronized (this) {
      Thread.sleep(currentOrder.getDistance() / speed);
    }
  }

  @Override
  public Order takeOrder() throws InterruptedException {
    Order takenOrder;
    if (trunk.isEmpty()) {
      takenOrder = pendingOrders.take();
    } else {
      takenOrder = pendingOrders.poll();
    }
    if (takenOrder != null) {
      takenOrder.changeStatus();
    }
    return takenOrder;
  }
}
