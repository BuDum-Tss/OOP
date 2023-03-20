package ru.nsu.fit.apotapova;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PizzeriaAppTest {

  @Test
  void start() {
    PizzeriaApp app = new PizzeriaApp("src/test/resources/ru/nsu/fit/apotapova/employees.txt");
    app.start();
  }
}