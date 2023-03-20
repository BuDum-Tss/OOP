package ru.nsu.fit.apotapova.employees.roles;

import ru.nsu.fit.apotapova.order.Order;

public interface Producer {

  void transferOrder(Order completedOrder) throws InterruptedException;
}