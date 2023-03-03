package ru.nsu.fit.apotapova.mythreadpool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Класс, распределяющий задачи между потоками.
 */
public class TaskManager implements Runnable {

  BlockingQueue<Callable<Boolean>> managerQueue;
  List<BlockingQueue<Callable<Boolean>>> workersQueues;
  List<Thread> threads;

  /**
   * Конструктор.
   *
   * @param managerQueue очередь нераспределенных задач
   * @param workersQueues список очередей распреденных задач
   * @param threads список потоков, в которых выполняются задачи
   */
  public TaskManager(BlockingQueue<Callable<Boolean>> managerQueue,
      List<BlockingQueue<Callable<Boolean>>> workersQueues,
      List<Thread> threads) {
    this.managerQueue = managerQueue;
    this.workersQueues = workersQueues;
    this.threads = threads;
  }

  @Override
  public void run() {
    try {
      Callable<Boolean> currentTask = null;
      int l = workersQueues.size();
      for (int i = 0; !(Thread.currentThread().isInterrupted() && managerQueue.isEmpty());
          i = (i + 1) % l) {
        if (currentTask == null || workersQueues.get(i).offer(currentTask)) {
          currentTask = managerQueue.take();
        }
      }
    } catch (InterruptedException e) {
      threads.forEach(Thread::interrupt);
      threads.forEach(thread -> {
        try {
          thread.join();
        } catch (InterruptedException f) {
          throw new RuntimeException(f);
        }
      });
    }
  }
}
