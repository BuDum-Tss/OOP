package ru.nsu.fit.apotapova.order;

import org.jetbrains.annotations.Nullable;

public enum OrderStatus {
  GENERATED(0, "Order is generated"),
  ADOPTED(1, "Order is adopted"),
  BAKING(2, "Order is baking"),
  AWAITING_DELIVERY(3, "Order is awaiting delivery"),
  DELIVERING(4, "Order is delivering"),
  DELIVERED(5, "Order is delivered");

  private final String value;
  private final int statusNumber;

  OrderStatus(int statusNumber, String value) {
    this.statusNumber = statusNumber;
    this.value = value;
  }

  private static OrderStatus getValue(int statusNumber) {
    for (OrderStatus orderStatus : values()) {
      if (orderStatus.getNumber() == statusNumber) {
        return orderStatus;
      }
    }
    return null;
  }

  public String getMessage() {
    return value;
  }

  private int getNumber() {
    return statusNumber;
  }

  public OrderStatus nextStatus() {
    return getValue(statusNumber + 1);
  }

  public boolean isFinal() {
    return statusNumber == 5;
  }
}
