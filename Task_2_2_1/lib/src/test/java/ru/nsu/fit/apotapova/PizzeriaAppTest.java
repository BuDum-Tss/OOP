package ru.nsu.fit.apotapova;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PizzeriaAppTest {

  @Test
  void start() {
    PizzeriaApp app = new PizzeriaApp("src/main/resources/ru/nsu/fit/apotapova/employees.txt",
        10000, 10,1000,1000);
    app.start();
  }
}