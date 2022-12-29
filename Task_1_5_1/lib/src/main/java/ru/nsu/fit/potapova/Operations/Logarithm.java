package ru.nsu.fit.potapova.Operations;

import ru.nsu.fit.potapova.Operation;

/**
 * Implements {@link Operation}. Calculates logarithm number based on another.
 */
public class Logarithm implements Operation {

  @Override
  public int numberOfArguments() {
    return 2;
  }

  @Override
  public Double calculate(Double[] arguments) {
    if (Math.log(arguments[0]) != 0) {
      return Math.log(arguments[1]) / Math.log(arguments[0]);
    }
    throw new RuntimeException("Attempt to divide by zero");
  }
}
