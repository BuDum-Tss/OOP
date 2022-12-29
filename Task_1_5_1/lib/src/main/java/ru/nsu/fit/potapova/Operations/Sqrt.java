package ru.nsu.fit.potapova.Operations;

import ru.nsu.fit.potapova.Operation;

public class Sqrt implements Operation {

  @Override
  public int numberOfArguments() {
    return 2;
  }

  @Override
  public Double calculate(Double[] arguments) {
    return Math.sqrt(arguments[0]);
  }
}
