package ru.nsu.fit.apotapova;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.nsu.fit.apotapova.mythreadpool.MyThreadPool;

/**
 * Реализация поиска не простых чисел в массиве через собственный ThreadPool.
 */
public class NotPrimeMyThreadPoolFinder extends NotPrimeFinder {

  private final MyThreadPool myThreadPool;

  public NotPrimeMyThreadPoolFinder(int numberThreads, int capacity) {
    this.myThreadPool = new MyThreadPool(numberThreads, capacity);
  }

  @Override
  public boolean hasNotPrime(@NonNull List<Integer> array)
      throws InterruptedException, ExecutionException {

    List<Callable<Boolean>> tasks = new ArrayList<>();
    for (Integer integer : array) {
      tasks.add(new Task(integer));
    }
    List<Future<Boolean>> futureList = myThreadPool.invokeAll(tasks);
    for (Future<Boolean> future : futureList) {
      if (future.get()) {
        return true;
      }
    }
    return false;
  }

  static class Task implements Callable<Boolean> {

    private final Integer value;

    public Task(Integer value) {
      this.value = value;
    }

    @Override
    public Boolean call() {
      return !isPrime(value);
    }
  }
}
