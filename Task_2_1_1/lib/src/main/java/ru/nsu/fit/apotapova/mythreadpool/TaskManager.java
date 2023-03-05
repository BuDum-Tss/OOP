package ru.nsu.fit.apotapova.mythreadpool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;

/**
 * Класс, распределяющий задачи между потоками.
 */
public class TaskManager implements Runnable {

  private final BlockingQueue<FutureTask<Boolean>> managerQueue;
  private final List<BlockingQueue<FutureTask<Boolean>>> workersQueues;
  private final List<Thread> threads;

  /**
   * Конструктор.
   *
   * @param managerQueue  очередь нераспределенных задач
   * @param workersQueues список очередей распреденных задач
   * @param threads       список потоков, в которых выполняются задачи
   */
  public TaskManager(BlockingQueue<FutureTask<Boolean>> managerQueue,
      List<BlockingQueue<FutureTask<Boolean>>> workersQueues,
      List<Thread> threads) {
    this.managerQueue = managerQueue;
    this.workersQueues = workersQueues;
    this.threads = threads;
  }

  @Override
  public void run() {
    FutureTask<Boolean> currentTask = null;
    int l = workersQueues.size();
    for (int i = 0; (!(Thread.currentThread().isInterrupted() && managerQueue.isEmpty()));
        i = (i + 1) % l) {
      if (currentTask == null || workersQueues.get(i).offer(currentTask)) {
        try {
          currentTask = managerQueue.take();
        } catch (InterruptedException ignored) {
          return;
        }
      }
    }
    Thread.currentThread().interrupt();
    threads.forEach(Thread::interrupt);
  }
}
