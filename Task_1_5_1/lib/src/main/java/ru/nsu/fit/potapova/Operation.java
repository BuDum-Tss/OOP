package ru.nsu.fit.potapova;

/**
 * Interface for operations.
 */
public interface Operation {

  int numberOfArguments();

  Double calculate(Double[] arguments);
}
