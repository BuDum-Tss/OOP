package ru.nsu.fit.potapova.Operations;

import ru.nsu.fit.potapova.Operation;

public class Logarithm implements Operation {

  @Override
  public int numberOfArguments() {
    return 2;
  }

  @Override
  public Double calculate(Double[] arguments) {
    return Math.log(arguments[1])/Math.log(arguments[0]);
  }
}
