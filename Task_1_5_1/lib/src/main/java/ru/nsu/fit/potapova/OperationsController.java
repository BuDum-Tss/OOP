package ru.nsu.fit.potapova;

import java.util.Map;
import ru.nsu.fit.potapova.Operations.Cos;
import ru.nsu.fit.potapova.Operations.Divide;
import ru.nsu.fit.potapova.Operations.Logarithm;
import ru.nsu.fit.potapova.Operations.Minus;
import ru.nsu.fit.potapova.Operations.Multiply;
import ru.nsu.fit.potapova.Operations.Plus;
import ru.nsu.fit.potapova.Operations.Power;
import ru.nsu.fit.potapova.Operations.Sin;
import ru.nsu.fit.potapova.Operations.Sqrt;

public class OperationsController {

  public static Map<String, Operation> operationMap = Map.of(
      "+", new Plus(),
      "-", new Minus(),
      "*", new Multiply(),
      "/", new Divide(),
      "sin",new Sin(),
      "cos", new Cos(),
      "log", new Logarithm(),
      "pow",new Power(),
      "sqrt",new Sqrt()
  );

  public static Operation define(String name) {
    return operationMap.get(name);
  }
}
