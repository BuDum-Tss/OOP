package ru.nsu.fit.potapova.Operations;

import ru.nsu.fit.potapova.Operation;

public class Power implements Operation {

  @Override
  public int numberOfArguments() {
    return 2;
  }

  @Override
  public Double calculate(Double[] arguments) {
    return Math.pow(arguments[0],arguments[1]);
  }
}
