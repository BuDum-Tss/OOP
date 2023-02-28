package ru.nsu.fit.apotapova;

import java.util.List;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Реализация поиска не простых чисел в массиве через Parallel stream.
 */
public class NotPrimeStreamFinder extends NotPrimeFinder {

  @Override
  public boolean hasNotPrime(@NonNull List<Integer> array) {
    return array.stream().parallel().anyMatch(number -> !isPrime(number));
  }
}
