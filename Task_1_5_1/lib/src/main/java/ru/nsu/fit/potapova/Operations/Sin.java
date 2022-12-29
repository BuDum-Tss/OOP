package ru.nsu.fit.potapova.Operations;

import ru.nsu.fit.potapova.Operation;

/**
 * Implements {@link Operation}. Finds the sine of a number.
 */
public class Sin implements Operation {

  @Override
  public int numberOfArguments() {
    return 1;
  }

  @Override
  public Double calculate(Double[] arguments) {
    return Math.sin(arguments[0]);
  }
}
