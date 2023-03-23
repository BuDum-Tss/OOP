package ru.nsu.fit.apotapova.employees;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The executor of the order is available for execution in the stream. Execution is interrupted by
 * the interrupt method.
 */
public abstract class OrderExecutor implements Runnable {

  protected final AtomicBoolean isInterrupted = new AtomicBoolean(false);
  protected final AtomicBoolean isWaitingOrder = new AtomicBoolean(false);
  private Thread currentThread;

  @Override
  public void run() {
    currentThread = Thread.currentThread();
    while (!isInterrupted.get()) {
      work();
    }
  }

  protected abstract void work();

  /**
   * Interrupts employee work. Employee finishes theirs taken orders.
   */
  public void interrupt() {
    isInterrupted.set(!isInterrupted.get());
    if (isWaitingOrder.get()) {
      currentThread.interrupt();
    }
  }
}
