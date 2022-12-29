package ru.nsu.fit.potapova.Operations;

import ru.nsu.fit.potapova.Operation;

public class Multiply implements Operation {

  @Override
  public int numberOfArguments() {
    return 2;
  }

  @Override
  public Double calculate(Double[] arguments) {
    return arguments[0] * arguments[1];
  }
}
