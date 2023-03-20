package ru.nsu.fit.apotapova;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import ru.nsu.fit.apotapova.employees.Baker;
import ru.nsu.fit.apotapova.employees.Courier;
import ru.nsu.fit.apotapova.employees.OrderExecutor;
import ru.nsu.fit.apotapova.json.BakerData;
import ru.nsu.fit.apotapova.json.CourierData;
import ru.nsu.fit.apotapova.json.EmployeesDataBase;
import ru.nsu.fit.apotapova.json.JSONReader;
import ru.nsu.fit.apotapova.order.Order;

public class Pizzeria implements Runnable {
  private final BlockingQueue<Order> pendingOrders;

  private final BlockingQueue<Order> storage;

  private Map<Integer, Baker> bakerHashmap;
  private Map<Integer, Courier> courierHashmap;
  private ExecutorService bakersThreadPool;
  private ExecutorService couriersThreadPool;

  public Pizzeria(File employeeData, int storageSize) {
    EmployeesDataBase data = new JSONReader(employeeData).read();
    storage = new LinkedBlockingQueue<>(storageSize);
    pendingOrders = new LinkedBlockingQueue<>();
    unpack(data);
  }
  public Pizzeria(int storageSize) {
    EmployeesDataBase data = new JSONReader().read();
    storage = new LinkedBlockingQueue<>(storageSize);
    pendingOrders = new LinkedBlockingQueue<>();
    unpack(data);
  }
  private void unpack(EmployeesDataBase data) {
    courierHashmap=new HashMap<>();
    bakerHashmap=new HashMap<>();
    data.getHashMap(BakerData.class).forEach((id, bakerData) -> bakerHashmap.put(id, new Baker(
        (BakerData) bakerData, pendingOrders, storage)));
    data.getHashMap(CourierData.class)
        .forEach((id, courierData) -> courierHashmap.put(id, new Courier(
            (CourierData) courierData, storage)));
  }
  private void prepareThreadPool(ExecutorService employeeThreadPool,Collection<? extends OrderExecutor> employeeMap) {
    employeeThreadPool = Executors.newFixedThreadPool(employeeMap.size());
    employeeMap.forEach(employeeThreadPool::execute);
  }
  public void addOrder(Order newOrder, boolean observe) {
    try {
      pendingOrders.put(newOrder);
      newOrder.changeStatus();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void addOrder(Order newOrder){
    addOrder(newOrder,true);
  }

  @Override
  public void run() {
    bakersThreadPool = Executors.newFixedThreadPool(bakerHashmap.size());
    bakerHashmap.values().forEach(bakersThreadPool::execute);
    couriersThreadPool = Executors.newFixedThreadPool(courierHashmap.size());
    courierHashmap.values().forEach(couriersThreadPool::execute);
    System.out.println("The pizzeria is open!");

    while (!Thread.currentThread().isInterrupted());

    System.err.println("Unexpected interruption");
  }
  private void terminate(ExecutorService employeeThreadPool,String employeesPosition,long overtime) {
    boolean timeOut;
    try {
      timeOut = !employeeThreadPool.awaitTermination(overtime, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException("Illegal interruption while waiting employees!");
    }
    if (timeOut) System.out.println(employeesPosition+"s time is out!");
  }

  public void stop() {
    bakerHashmap.forEach((id, baker) -> baker.interrupt());
    courierHashmap.forEach((id, courier) -> courier.interrupt());
    terminate(bakersThreadPool,"Baker",10);
    terminate(couriersThreadPool,"Courier",10);
    System.out.println("The pizzeria is closed!");
  }
}
