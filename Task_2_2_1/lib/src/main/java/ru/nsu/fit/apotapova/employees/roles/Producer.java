package ru.nsu.fit.apotapova.employees.roles;

import ru.nsu.fit.apotapova.order.Order;

/**
 * An interface for order executors that provides an object with access to add a processed order to
 * the order queue.
 */
public interface Producer {

  /**
   * Transfer the order to the order queue.
   *
   * @param completedPizzaOrder transferred order
   */
  void transferOrder(Order completedPizzaOrder);
}