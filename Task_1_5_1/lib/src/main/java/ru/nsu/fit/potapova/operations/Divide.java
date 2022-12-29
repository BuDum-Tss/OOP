package ru.nsu.fit.potapova.operations;

import ru.nsu.fit.potapova.Operation;

/**
 * Implements {@link Operation}. Divides one number by another.
 */
public class Divide implements Operation {

  @Override
  public int numberOfArguments() {
    return 2;
  }

  @Override
  public Double calculate(Double[] arguments) {
    if (arguments[1] != 0) {
      return arguments[0] / arguments[1];
    }
    throw new RuntimeException("Attempt to divide by zero");
  }
}
