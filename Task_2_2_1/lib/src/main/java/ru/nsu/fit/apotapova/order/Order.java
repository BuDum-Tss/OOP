package ru.nsu.fit.apotapova.order;

import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.apotapova.NotificationSystem;

/**
 * Class that contains number, distance of the order and status.
 */
public class Order {

  private final long distance;
  private final AtomicBoolean isUpdated;
  private Integer number = null;
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

  /**
   * Constructor.
   *
   * @param status order status
   */
  public Order(@NotNull OrderStatus status) {
    this.status = status;
    this.distance = 0;
    isUpdated = new AtomicBoolean(true);
  }

  /**
   * Changes order status and sends message to NotificationSystem.
   */
  public synchronized void changeStatus() {
    OrderStatus newStatus = status.nextStatus();
    if (newStatus != null) {
      status = status.nextStatus();
      isUpdated.set(true);
    }
    NotificationSystem.newMessage("[" + number + "]" + status.getMessage());
  }

  public long getDistance() {
    return distance;
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
}
