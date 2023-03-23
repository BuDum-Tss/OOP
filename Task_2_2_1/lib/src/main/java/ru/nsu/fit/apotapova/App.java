package ru.nsu.fit.apotapova;

/**
 * Application entry point.
 */
public class App {

  /**
   * Main method.
   *
   * @param args args of command line
   */
  public static void main(String[] args) {

    PizzeriaApp app = new PizzeriaApp("lib/src/main/resources/ru/nsu/fit/apotapova/employees.txt",
        10000, 10, 1000, 1000);
    app.start();
  }
}
