package ru.nsu.fit.apotapova;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Обычная реализация поиска не простых чисел в массиве.
 */
public class NotPrimeFinder {

  protected static boolean isPrime(Integer number) {
    if (number <= 1) {
      return false;
    }
    int n = number / 2;
    int k = 2;
    while (number % k != 0 && k <= n) {
      k++;
    }
    return k > n;
  }

  /**
   * Определяет, есть ли в заданном массиве хотя бы одно не простое число.
   *
   * @param array массив
   * @return true если есть, иначе false
   * @throws InterruptedException сигнал потоку остановиться
   * @throws ExecutionException   исключение, вызванное в потоке
   */
  public boolean hasNotPrime(@NonNull List<Integer> array)
      throws InterruptedException, ExecutionException {
    for (Integer number : array) {
      if (!isPrime(number)) {
        return true;
      }
    }
    return false;
  }
}
