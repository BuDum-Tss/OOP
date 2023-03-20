package ru.nsu.fit.apotapova.employees;

import java.util.concurrent.BlockingQueue;
import ru.nsu.fit.apotapova.employees.roles.Consumer;
import ru.nsu.fit.apotapova.employees.roles.Producer;
import ru.nsu.fit.apotapova.json.BakerData;
import ru.nsu.fit.apotapova.order.Order;

public class Baker extends OrderExecutor implements Consumer, Producer {

  private final long bakingTime;
  private BlockingQueue<Order> pendingOrders;
  private BlockingQueue<Order> storage;

  public Baker(BakerData bakerData, BlockingQueue<Order> pendingOrders,
      BlockingQueue<Order> storage) {
    this.bakingTime = bakerData.getBakingTime();
    this.pendingOrders = pendingOrders;
    this.storage = storage;
  }

  @Override
  public Order takeOrder() throws InterruptedException {
    Order takenOrder = pendingOrders.take();
    takenOrder.changeStatus();
    return takenOrder;
  }

  @Override
  protected void work() throws InterruptedException {
    Order currentOrder = takeOrder();
    bake();
    transferOrder(currentOrder);
  }

  private void bake() throws InterruptedException {
    synchronized (this) {
      Thread.sleep(bakingTime);
    }
  }

  @Override
  public final void transferOrder(Order completedOrder) throws InterruptedException {
    storage.put(completedOrder);
    completedOrder.changeStatus();
  }
}
