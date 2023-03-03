package ru.nsu.fit.apotapova.mythreadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Класс, выполняющий полученные задачи.
 */
public class TaskWorker implements Runnable {

  BlockingQueue<Callable<Boolean>> queue;
  BlockingQueue<Boolean> output;

  /**
   * Конструктор.
   *
   * @param queue очередь из полученных задач
   * @param output очередь, для полученнного результата
   */
  public TaskWorker(BlockingQueue<Callable<Boolean>> queue, BlockingQueue<Boolean> output) {
    this.queue = queue;
    this.output = output;
  }

  @Override
  public void run() {
    try {
      while (!(queue.isEmpty() && Thread.currentThread().isInterrupted())) {
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