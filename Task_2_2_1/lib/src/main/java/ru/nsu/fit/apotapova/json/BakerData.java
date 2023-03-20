package ru.nsu.fit.apotapova.json;

import ru.nsu.fit.apotapova.employees.Baker;
import ru.nsu.fit.apotapova.employees.OrderExecutor;

public class BakerData extends EmployeeData {
  private long bakingTime;
  public long getBakingTime() {
    return bakingTime;
  }

  public static Class<? extends OrderExecutor> getAppropriateClass(){
    return Baker.class;
  }
}
