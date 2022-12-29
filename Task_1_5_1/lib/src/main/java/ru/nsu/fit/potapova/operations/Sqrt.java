package ru.nsu.fit.potapova.operations;

import ru.nsu.fit.potapova.Operation;

/**
 * Implements {@link Operation}. Finds the square root of a number.
 */
public class Sqrt implements Operation {

  @Override
  public int numberOfArguments() {
    return 2;
  }

  @Override
  public Double calculate(Double[] arguments) {
    if (arguments[0]>=0) return Math.sqrt(arguments[0]);
    throw new RuntimeException("Trying to take the root of a negative number");
  }
}
