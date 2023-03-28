package ru.nsu.fit.apotapova.order;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Status of order. Contains status number and message.
 */
public enum OrderStatus {
  SPECIAL(-1, "Stop work"),
  GENERATED(0, "Order is generated"),

  ADOPTED(1, "Order is adopted"),
  BAKING(2, "Order is baking"),
  AWAITING_DELIVERY(3, "Order is awaiting delivery"),
  DELIVERING(4, "Order is delivering"),
  DELIVERED(5, "Order is delivered");

  private static final Map<Integer, OrderStatus> valueMap = Arrays.stream(values())
      .filter(orderStatus -> orderStatus != SPECIAL)
      .collect(
          Collectors.toMap(orderStatus -> orderStatus.statusNumber, orderStatus -> orderStatus));
  private final String value;
  private final int statusNumber;

  OrderStatus(int statusNumber, String value) {
    this.statusNumber = statusNumber;
    this.value = value;
  }

  private static OrderStatus getValue(int statusNumber) {
    return valueMap.get(statusNumber);
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
}
