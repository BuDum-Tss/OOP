package ru.nsu.fit.apotapova.employees.roles;

import ru.nsu.fit.apotapova.order.Order;

public interface Consumer {

  Order takeOrder() throws InterruptedException;
}
