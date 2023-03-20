package ru.nsu.fit.apotapova;


public class PizzeriaApp {

  public static void main(String[] args) {

    Pizzeria pizzeria = new Pizzeria(10);
    OrderGenerator orderGenerator = new OrderGenerator(pizzeria);

    Thread pizzeriaThread = new Thread(pizzeria);
    Thread orderGeneratorThread = new Thread(orderGenerator);

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
