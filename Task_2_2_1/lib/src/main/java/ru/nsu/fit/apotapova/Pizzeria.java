package ru.nsu.fit.apotapova;

import static java.lang.Thread.sleep;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import ru.nsu.fit.apotapova.employees.Baker;
import ru.nsu.fit.apotapova.employees.Courier;
import ru.nsu.fit.apotapova.json.EmployeesDataBase;
import ru.nsu.fit.apotapova.order.Order;
import ru.nsu.fit.apotapova.order.OrderStatus;

/**
 * Main Pizzeria class.
 */
public class Pizzeria implements Runnable {

  private static final Integer OVERTIME = 60;
  private Integer orderNumber;
  private BlockingQueue<Order> pendingOrders;
  private BlockingQueue<Order> storage;
  private Map<Integer, Baker> bakerHashmap;
  private Map<Integer, Courier> courierHashmap;
  private ExecutorService bakersThreadPool;
  private ExecutorService couriersThreadPool;
  private AtomicBoolean isInterrupted;
  private Thread currentThread;

  /**
   * Constructor.
   *
   * @param data        employees data
   * @param storageSize storage size
   */
  public Pizzeria(EmployeesDataBase data, int storageSize) {
    init(storageSize);
    unpack(data);
  }

  private void init(int storageSize) {
    orderNumber = 0;
    isInterrupted = new AtomicBoolean(false);
    storage = new LinkedBlockingQueue<>(storageSize);
    pendingOrders = new LinkedBlockingQueue<>();
  }

  private void unpack(EmployeesDataBase data) {
    courierHashmap = new HashMap<>();
    bakerHashmap = new HashMap<>();
    data.getBakerHashMap().forEach((id, bakerData) -> bakerHashmap.put(id, new Baker(
        bakerData, pendingOrders, storage)));
    data.getCourierHashMap()
        .forEach((id, courierData) -> courierHashmap.put(id, new Courier(courierData, storage)));
  }

  /**
   * Adds order to queue of pending orders.
   *
   * @param newOrder pending order
   */
  public void addOrder(Order newOrder) {
    if (isInterrupted.get()) {
      return;
    }
    try {
      newOrder.setNumber(orderNumber++);
      pendingOrders.put(newOrder);
    } catch (InterruptedException e) {
      throw new RuntimeException("Unexpected Interruption while putting order to queue");
    }
  }

  @Override
  public void run() {
    currentThread = Thread.currentThread();
    bakersThreadPool = Executors.newFixedThreadPool(bakerHashmap.size());
    bakerHashmap.values().forEach(bakersThreadPool::execute);
    couriersThreadPool = Executors.newFixedThreadPool(courierHashmap.size());
    courierHashmap.values().forEach(couriersThreadPool::execute);
    System.out.println("The pizzeria is open!");
    try {
      sleep(Integer.MAX_VALUE);
    } catch (InterruptedException e) {
      if (!isInterrupted.get()) {
        throw new RuntimeException("Unexpected pizzeria interruption");
      }
    }
  }

  private void addSpecialTasks(int number, BlockingQueue<Order> queue) {
    for (int i = 0; i < number; i++) {
      try {
        queue.put(new Order(OrderStatus.SPECIAL));
      } catch (InterruptedException e) {
        throw new RuntimeException("Unexpected Interruption while putting order to queue");
      }
    }
  }

  /**
   * Interrupts pizzeria work.
   */
  public void interrupt() {
    isInterrupted.set(true);
    addSpecialTasks(bakerHashmap.size(), pendingOrders);
    bakersThreadPool.shutdown();
    terminate(bakersThreadPool, "Baker");
    addSpecialTasks(courierHashmap.size(), storage);
    couriersThreadPool.shutdown();
    terminate(couriersThreadPool, "Courier");
    System.out.println("The pizzeria is closed!");
    currentThread.interrupt();
  }

  private void terminate(ExecutorService employeeThreadPool, String employeesPosition) {
    boolean timeOut;
    try {
      timeOut = !employeeThreadPool.awaitTermination(OVERTIME, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(
          "Illegal interruption Thread" + Thread.currentThread().getName()
              + " while waiting employees!");
    }
    if (timeOut) {
      System.out.println(employeesPosition + "s time is out!");
    }
  }
}
