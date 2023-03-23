package ru.nsu.fit.apotapova.employees;

import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.apotapova.employees.roles.Consumer;
import ru.nsu.fit.apotapova.employees.roles.Producer;
import ru.nsu.fit.apotapova.json.BakerData;
import ru.nsu.fit.apotapova.order.Order;
import ru.nsu.fit.apotapova.order.Order.OrderStatusMod;

/**
 * The baker class receives an order from a queue of pending orders, bakes, and passes it to the
 * warehouse.
 */
public class Baker extends OrderExecutor implements Consumer, Producer {

  private final long bakingTime;
  private final BlockingQueue<Order> pendingOrders;
  private final BlockingQueue<Order> storage;

  /**
   * Constructor.
   *
   * @param bakerData     data with baker speed
   * @param pendingOrders the queue from where the baker takes the order
   * @param storage       the queue where the baker transfers the order
   */
  public Baker(BakerData bakerData, BlockingQueue<Order> pendingOrders,
      BlockingQueue<Order> storage) {
    this.bakingTime = bakerData.getBakingTime();
    this.pendingOrders = pendingOrders;
    this.storage = storage;
  }

  @Override
  public Order takeOrder() {
    Order takenOrder = null;
    try {
      synchronized (this) {
        isWaitingOrder.set(true);
        takenOrder = pendingOrders.take();
        isWaitingOrder.set(false);
      }
      takenOrder.manageStatus(OrderStatusMod.CHANGE);
    } catch (InterruptedException e) {
      if (!isInterrupted.get()) {
        throw new RuntimeException(
            "Unexpected interruption while taking the order at:" + this.getClass().toString());
      }
    }
    return takenOrder;
  }

  @Override
  protected void work() {
    Order currentOrder = takeOrder();
    if (currentOrder == null) {
      return;
    }
    bake();
    transferOrder(currentOrder);
  }

  private void bake() {
    synchronized (this) {
      try {
        Thread.sleep(bakingTime);
      } catch (InterruptedException e) {
        throw new RuntimeException(
            "Unexpected interruption while sleeping at: " + this.getClass().toString());
      }
    }
  }

  @Override
  public final void transferOrder(Order completedOrder) {
    try {
      storage.put(completedOrder);
    } catch (InterruptedException e) {
      if (!isInterrupted.get()) {
        throw new RuntimeException(
            "Unexpected interruption while transferring the order at: " + this.getClass()
                .toString());
      }
    }
    completedOrder.manageStatus(OrderStatusMod.CHANGE);
  }
}
