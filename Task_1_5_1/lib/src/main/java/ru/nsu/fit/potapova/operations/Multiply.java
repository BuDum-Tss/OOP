package ru.nsu.fit.potapova.operations;

import ru.nsu.fit.potapova.Operation;

/**
 * Implements {@link Operation}. Multiplies numbers.
 */
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
