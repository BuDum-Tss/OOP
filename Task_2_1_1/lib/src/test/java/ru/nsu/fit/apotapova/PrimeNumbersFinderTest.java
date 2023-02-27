package ru.nsu.fit.apotapova;

import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimeNumbersFinderTest {

  @Test
  void notPrimeFinder() {
    NotPrimeFinder npf = new NotPrimeFinder();
    try {
      Assertions.assertTrue(npf.hasNotPrime(new Integer[]{6, 8, 7, 13, 9, 4}));
      Assertions.assertFalse(npf.hasNotPrime(
          new Integer[]{6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053}));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void notPrimeStreamFinder() {
    NotPrimeFinder npf = new NotPrimeStreamFinder();
    try {
      Assertions.assertTrue(npf.hasNotPrime(new Integer[]{6, 8, 7, 13, 9, 4}));
      Assertions.assertFalse(npf.hasNotPrime(
          new Integer[]{6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053}));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  void notPrimeStandardThreadPoolFinder() {
    NotPrimeFinder npf = new NotPrimeStandardThreadPoolFinder(3);
    try {
      Assertions.assertTrue(npf.hasNotPrime(new Integer[]{6, 8, 7, 13, 9, 4}));
      Assertions.assertFalse(npf.hasNotPrime(
          new Integer[]{6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053}));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void notPrimeMyThreadPoolFinder() {
    NotPrimeFinder npf = new NotPrimeMyThreadPoolFinder(3);
    try {
      Assertions.assertTrue(npf.hasNotPrime(new Integer[]{6, 8, 7, 13, 9, 4}));
      Assertions.assertFalse(npf.hasNotPrime(
          new Integer[]{6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053}));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}