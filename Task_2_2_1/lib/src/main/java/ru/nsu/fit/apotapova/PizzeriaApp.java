package ru.nsu.fit.apotapova;


import java.io.File;
import ru.nsu.fit.apotapova.json.JSONReader;

/**
 * Class for launch and construct pizzeria.
 */
public class PizzeriaApp {

  private final long workingTime;
  private final OrderGenerator orderGenerator;
  private final Pizzeria pizzeria;
  private final NotificationSystem notificationSystem;
  private Thread pizzeriaThread;
  private Thread orderGeneratorThread;
  private Thread notificationSystemThread;

  /**
   * Class for launch and construct pizzeria.
   *
   * @param jsonPath            path of json
   * @param workingTime         pizzeria working time(milliseconds)
   * @param storageSize         storage size
   * @param maxDistance         maximum distance of delivering(m)
   * @param frequencyOfRequests frequencyOfRequests frequency of requests(milliseconds)
   */
  public PizzeriaApp(String jsonPath, long workingTime, Integer storageSize, int maxDistance,
      int frequencyOfRequests) {
    this.workingTime = workingTime;
    this.notificationSystem = new NotificationSystem();
    this.pizzeria = new Pizzeria(new JSONReader().read(new File(jsonPath)), storageSize,
        notificationSystem);
    this.orderGenerator = new OrderGenerator(pizzeria, notificationSystem);
    orderGenerator.setNewSettings(maxDistance, frequencyOfRequests);
    initThreads();
  }

  private void initThreads() {
    pizzeriaThread = new Thread(pizzeria, "Pizzeria");
    orderGeneratorThread = new Thread(orderGenerator, "OrderGenerator");
    notificationSystemThread = new Thread(notificationSystem, "Notification System");
  }

  /**
   * Starts pizzeria work.
   */
  public void start() {
    notificationSystemThread.start();
    pizzeriaThread.start();
    orderGeneratorThread.start();
    try {
      Thread.sleep(workingTime);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    orderGeneratorThread.interrupt();
    pizzeria.interrupt();
    pizzeriaThread.interrupt();
    notificationSystemThread.interrupt();
  }
}
