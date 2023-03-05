package ru.nsu.fit.apotapova.mythreadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;

/**
 * Класс, выполняющий полученные задачи.
 */
public class TaskWorker implements Runnable {

  private final BlockingQueue<FutureTask<Boolean>> queue;

  /**
   * Конструктор.
   *
   * @param queue очередь из полученных задач
   */
  public TaskWorker(BlockingQueue<FutureTask<Boolean>> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {

      while (!(queue.isEmpty() && Thread.currentThread().isInterrupted())) {
        if (!queue.isEmpty()) {
          try {
          FutureTask<Boolean> task = queue.take();
            task.run();
          } catch (InterruptedException ignored) {
          }
        }
      }
    Thread.currentThread().interrupt();
  }
}