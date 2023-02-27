package ru.nsu.fit.apotapova;

import java.util.*;
import java.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Реализация поиска не простых чисел в массиве через newFixedThreadPool.
 */
public class NotPrimeStandardThreadPoolFinder extends NotPrimeFinder {

  private final ExecutorService pool;
  private Deque<Integer> deque;

  public NotPrimeStandardThreadPoolFinder(int numberThreads) {
    this.pool = Executors.newFixedThreadPool(numberThreads);
  }

  private synchronized Integer getNumber() {
    if (deque.isEmpty()) {
      return null;
    }
    return deque.pop();
  }

  @Override
  public boolean hasNotPrime(@NonNull Integer[] array)
      throws InterruptedException, ExecutionException {
    deque = new ArrayDeque<>(Arrays.stream(array).toList());

    Callable<Boolean> task = () -> {
      Integer number;
      while ((number = getNumber()) != null) {
        if (!isPrime(number)) {
          return true;
        }
      }
      return false;
    };
    List<Callable<Boolean>> tasks = new ArrayList<>();
    for (int i = 0; i < array.length; ++i) {
      tasks.add(task);
    }
    List<Future<Boolean>> futureList = pool.invokeAll(tasks);
    for (Future<Boolean> future : futureList) {
      if (future.get()) {
        pool.shutdownNow();
        return true;
      }
    }
    pool.shutdownNow();
    return false;
  }
}
