package ru.nsu.fit.apotapova.mythreadpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Реализация ThreadPool.
 */
public class MyThreadPool {

  final Thread managerThread;
  private final List<Thread> threads;
  private final List<BlockingQueue<Callable<Boolean>>> threadsQueues;
  private final BlockingQueue<Callable<Boolean>> managerQueue;
  private final BlockingQueue<Boolean> output;
  int numberThreads;

  /**
   * Конструктор.
   *
   * @param numberThreads количество потоков
   * @param capacity      вместимость очереди задач потока
   */
  public MyThreadPool(int numberThreads, int capacity) {
    this.numberThreads = numberThreads;
    managerQueue = new LinkedBlockingQueue<>();
    threads = new ArrayList<>();
    threadsQueues = new ArrayList<>();
    output = new LinkedBlockingQueue<>();
    for (int threadNumber = 0; threadNumber < numberThreads; threadNumber++) {
      startWorkerThreads(capacity);
    }
    TaskManager taskManager = new TaskManager(managerQueue, threadsQueues, threads);
    managerThread = new Thread(taskManager);
    managerThread.start();
  }

  private void startWorkerThreads(int capacity) {
    BlockingQueue<Callable<Boolean>> input = new LinkedBlockingQueue<>(capacity);
    threadsQueues.add(input);
    TaskWorker taskWorker = new TaskWorker(input, output);
    Thread thread = new Thread(taskWorker);
    threads.add(thread);
    thread.start();
  }

  /**
   * Добавить задачи.
   *
   * @param tasks задачи
   */
  public void addTasks(Collection<Callable<Boolean>> tasks) {
    managerQueue.addAll(tasks);
  }

  /**
   * Получить результат.
   *
   * @return результат
   */
  public List<Boolean> getResult() {
    managerThread.interrupt();
    try {
      managerThread.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    List<Boolean> answer = new ArrayList<>(output);
    clear();
    return answer;
  }

  private void clear() {
    managerQueue.clear();
    output.clear();
    threadsQueues.forEach(Collection::clear);
  }
}
