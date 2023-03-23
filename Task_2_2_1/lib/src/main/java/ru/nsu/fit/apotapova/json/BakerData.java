package ru.nsu.fit.apotapova.json;

import ru.nsu.fit.apotapova.employees.Baker;
import ru.nsu.fit.apotapova.employees.OrderExecutor;

/**
 * Class for baker's data for JSON.
 */
public class BakerData {

  private long bakingTime;

  public static Class<? extends OrderExecutor> getAppropriateClass() {
    return Baker.class;
  }

  public long getBakingTime() {
    return bakingTime;
  }
}
