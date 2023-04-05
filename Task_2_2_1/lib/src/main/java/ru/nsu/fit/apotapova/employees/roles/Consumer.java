package ru.nsu.fit.apotapova.employees.roles;

import ru.nsu.fit.apotapova.order.Order;

/**
 * An interface for order executors that provides an object with access to receive an order from an
 * order queue.
 */
public interface Consumer {

  /**
   * Take an order from the order queue.
   *
   * @return accepted order
   */
  Order takeOrder();
}
