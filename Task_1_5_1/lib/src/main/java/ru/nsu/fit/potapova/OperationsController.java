package ru.nsu.fit.potapova;

import java.util.Map;
import ru.nsu.fit.potapova.Operations.Minus;
import ru.nsu.fit.potapova.Operations.Plus;

public class OperationsController {

  public static Map<String, Operation> operationMap = Map.of(
      "+", new Plus(),
      "-", new Minus()
  );

  public static Operation define(String name) {
    return operationMap.get(name);
  }
}
