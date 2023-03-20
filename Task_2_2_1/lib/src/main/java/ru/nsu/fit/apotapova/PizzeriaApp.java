package ru.nsu.fit.apotapova;


import java.io.File;

public class PizzeriaApp {
  OrderGenerator orderGenerator;
  Pizzeria pizzeria;
  Thread pizzeriaThread;
  Thread orderGeneratorThread;
  public PizzeriaApp(String jsonPath) {
    pizzeria = new Pizzeria(new File(jsonPath),10);
    orderGenerator = new OrderGenerator(pizzeria);

    pizzeriaThread = new Thread(pizzeria);
    orderGeneratorThread = new Thread(orderGenerator);
  }
  public PizzeriaApp() {
    pizzeria = new Pizzeria(10);
    orderGenerator = new OrderGenerator(pizzeria);

    pizzeriaThread = new Thread(pizzeria);
    orderGeneratorThread = new Thread(orderGenerator);
  }

  public void start() {
    pizzeriaThread.start();
    orderGeneratorThread.start();
    Object lock = new Object();

    try {
      Thread.sleep(1000 * 10 * 1);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    orderGeneratorThread.interrupt();
    pizzeria.stop();
  }
}
