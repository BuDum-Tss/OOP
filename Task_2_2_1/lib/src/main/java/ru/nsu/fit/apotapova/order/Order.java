package ru.nsu.fit.apotapova.order;

import org.jetbrains.annotations.NotNull;

public class Order{

  private final int number;
  private final long distance;
  private @NotNull OrderStatus status;


  public Order(int number, long distance) {
    status = OrderStatus.GENERATED;
    this.number = number;
    this.distance = distance;
  }

  public @NotNull OrderStatus getStatus() {
    return status;
  }

  public void setStatus(@NotNull OrderStatus status) {
    this.status = status;
  }

  public long getDistance() {
    return distance;
  }

  public void changeStatus() {
    OrderStatus newStatus = status.nextStatus();
    if (newStatus != null) {
      status = status.nextStatus();
    }
    System.out.println("[" + number + "]: " + status.getMessage());
  }

  public int getNumber() {
    return number;
  }
}
