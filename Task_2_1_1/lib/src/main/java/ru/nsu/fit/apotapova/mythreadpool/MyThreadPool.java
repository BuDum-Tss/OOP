package ru.nsu.fit.apotapova.mythreadpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Реализация ThreadPool.
 */
public class MyThreadPool {

  private final Thread managerThread;
  private final BlockingQueue<FutureTask<Boolean>> managerQueue;
  private final List<BlockingQueue<FutureTask<Boolean>>> threadsQueues;
  private final List<Thread> threads;

  /**
   * Конструктор.
   *
   * @param numberThreads количество потоков
   * @param capacity      вместимость очереди задач потока
   */
  public MyThreadPool(int numberThreads, int capacity) {
    managerQueue = new LinkedBlockingQueue<>();
    threads = new ArrayList<>();
    threadsQueues = new ArrayList<>();
    for (int threadNumber = 0; threadNumber < numberThreads; threadNumber++) {
      initWorkerThreads(capacity);
    }
    TaskManager taskManager = new TaskManager(managerQueue, threadsQueues, threads);
    managerThread = new Thread(taskManager, "Manager");
    startThreads();
  }

  private void initWorkerThreads(int capacity) {
    BlockingQueue<FutureTask<Boolean>> input = new LinkedBlockingQueue<>(capacity);
    threadsQueues.add(input);
    TaskWorker taskWorker = new TaskWorker(input);
    Thread thread = new Thread(taskWorker);
    threads.add(thread);
  }

  /**
   * Добавить задачи.
   *
   * @param tasks задачи
   */
  public List<Future<Boolean>> invokeAll(Collection<Callable<Boolean>> tasks) {
    List<Future<Boolean>> outputList = new ArrayList<>();
    tasks.forEach(task -> addTask(task, outputList));
    managerThread.interrupt();
    return outputList;
  }

  private void addTask(Callable<Boolean> task, List<Future<Boolean>> outputList) {
    try {
      FutureTask<Boolean> futureTask = new FutureTask<>(task);
      outputList.add(futureTask);
      managerQueue.put(futureTask);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void startThreads() {
    threads.forEach(Thread::start);
    managerThread.start();
  }
}
