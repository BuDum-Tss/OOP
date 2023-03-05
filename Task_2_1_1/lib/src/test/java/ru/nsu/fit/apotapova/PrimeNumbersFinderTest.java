package ru.nsu.fit.apotapova;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimeNumbersFinderTest {

  @Test
  void notPrimeFinder() {
    NotPrimeFinder npf = new NotPrimeFinder();
    try {
      Assertions.assertTrue(npf.hasNotPrime(List.of(6, 8, 7, 13, 9, 4)));
      Assertions.assertFalse(npf.hasNotPrime(
          List.of(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053)));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void notPrimeStreamFinder() {
    NotPrimeFinder npsf = new NotPrimeStreamFinder();
    try {
      Assertions.assertTrue(npsf.hasNotPrime(List.of(6, 8, 7, 13, 9, 4)));
      Assertions.assertFalse(npsf.hasNotPrime(
          List.of(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053)));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  void notPrimeStandardThreadPoolFinder() {
    NotPrimeFinder npstpf = new NotPrimeStandardThreadPoolFinder(3);
    try {
      Assertions.assertTrue(npstpf.hasNotPrime(List.of(6, 8, 7, 13, 9, 4)));
      Assertions.assertFalse(npstpf.hasNotPrime(
          List.of(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053)));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void notPrimeMyThreadPoolFinder() {
    NotPrimeMyThreadPoolFinder npmtpf = new NotPrimeMyThreadPoolFinder(3,3);
    try {
      Assertions.assertTrue(npmtpf.hasNotPrime(List.of(6997901, 6997927, 6997937, 6997967, 6, 8, 7, 13, 9, 4)));
      Assertions.assertFalse(npmtpf.hasNotPrime(
          List.of(6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051,
              6998053)));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}