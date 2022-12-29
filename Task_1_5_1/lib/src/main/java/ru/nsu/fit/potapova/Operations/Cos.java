package ru.nsu.fit.potapova.Operations;

import ru.nsu.fit.potapova.Operation;

/**
 * Implements {@link Operation}. Calculates cosine of number.
 */
public class Cos implements Operation {

  @Override
  public int numberOfArguments() {
    return 1;
  }

  @Override
  public Double calculate(Double[] arguments) {
    return Math.cos(arguments[0]);
  }
}
