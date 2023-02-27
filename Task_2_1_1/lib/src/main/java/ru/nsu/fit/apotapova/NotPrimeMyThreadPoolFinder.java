package ru.nsu.fit.apotapova;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Реализация поиска не простых чисел в массиве через собственный ThreadPool.
 */
public class NotPrimeMyThreadPoolFinder extends NotPrimeFinder {

  private final MyThreadPool myThreadPool;

  public NotPrimeMyThreadPoolFinder(int numberThreads) {
    this.myThreadPool = new MyThreadPool(numberThreads);
  }

  @Override
  public boolean hasNotPrime(@NonNull Integer[] array) {
    List<Callable<Boolean>> tasks = new ArrayList<>();
    for (Integer integer : array) {
      tasks.add(new Task(integer));
    }
    List<Boolean> ansList = myThreadPool.invokeAll(tasks);
    return ansList.stream().anyMatch(answer -> true);
  }

  static class Task implements Callable<Boolean> {

    private final Integer value;

    public Task(Integer value) {
      this.value = value;
    }

    @Override
    public Boolean call() {
      return isPrime(value);
    }
  }

  private static class MyThreadPool {

    private final List<Thread> threads;
    private final BlockingQueue<Callable<Boolean>> queue;
    private final BlockingQueue<Boolean> output;
    int numberThreads;

    private MyThreadPool(int numberThreads) {
      this.numberThreads = numberThreads;
      queue = new LinkedBlockingQueue<>();
      threads = new ArrayList<>();
      output = new LinkedBlockingQueue<>();
      for (int count = 0; count < numberThreads; count++) {
        TaskWorker taskExecutor = new TaskWorker(queue, output);
        Thread thread = new Thread(taskExecutor);
        threads.add(thread);
        thread.start();
      }
    }

    public List<Boolean> invokeAll(Collection<? extends Callable<Boolean>> tasks) {
      queue.clear();
      output.clear();
      queue.addAll(tasks);
      threads.forEach(Thread::interrupt);
      return output.stream().toList();
    }
  }

  static class TaskWorker implements Runnable {

    BlockingQueue<Callable<Boolean>> queue;
    BlockingQueue<Boolean> output;

    public TaskWorker(BlockingQueue<Callable<Boolean>> queue, BlockingQueue<Boolean> output) {
      this.queue = queue;
      this.output = output;
    }

    @Override
    public void run() {
        {
        try {
          while (true) {
            if (queue.isEmpty() && Thread.currentThread().isInterrupted()) {
              return;
            }
            if (!queue.isEmpty()) {
              Callable<Boolean> task;

              task = queue.take();
              output.put(task.call());

            }
          }
        } catch (Exception e) {
          Thread.currentThread().interrupt();
        }
        }
    }
  }
}
