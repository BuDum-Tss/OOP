package ru.nsu.fit.apotapova.order;

import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/**
 * Class that contains number, distance of the order and status.
 */
public class Order {

  private final long distance;
  private Integer number = null;
  private final AtomicBoolean isUpdated;
  private volatile @NotNull OrderStatus status;

  /**
   * Constructor.
   *
   * @param distance distance
   */
  public Order(long distance) {
    status = OrderStatus.GENERATED;
    this.distance = distance;
    isUpdated = new AtomicBoolean(true);
  }

  private synchronized void synchronizedChangeStatus() {
    OrderStatus newStatus = status.nextStatus();
    if (newStatus != null) {
      status = status.nextStatus();
      isUpdated.set(true);
    }
  }

  /**
   * Manages order status.
   *
   * @param workingMod CHANGE or GET
   * @return message if mod is GET and status was updated else null
   */
  public synchronized String manageStatus(OrderStatusMod workingMod) {
    if (workingMod == OrderStatusMod.CHANGE) {
      synchronizedChangeStatus();
      return null;
    } else {
      return synchronizedGetStatus();
    }
  }

  private synchronized String synchronizedGetStatus() {
    if (isUpdated.getAndSet(false)) {
      return status.getMessage();
    } else {
      return null;
    }
  }

  public long getDistance() {
    return distance;
  }

  public int getNumber() {
    return number;
  }

  /**
   * Sets number of Order.
   *
   * @param number number of order
   */
  public void setNumber(Integer number) {
    if (this.number == null) {
      this.number = number;
    } else {
      throw new IllegalArgumentException("Number of order already exists");
    }
  }

  public @NotNull OrderStatus getStatus() {
    return status;
  }

  /**
   * Mod of managing order status.
   */
  public enum OrderStatusMod {
    CHANGE,
    GET
  }

}
