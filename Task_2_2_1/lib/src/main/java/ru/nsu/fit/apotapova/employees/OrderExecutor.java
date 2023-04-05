package ru.nsu.fit.apotapova.employees;

/**
 * The executor of the order is available for execution in the stream. Execution is interrupted by
 * the interrupt method.
 */
public abstract class OrderExecutor implements Runnable {

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      work();
    }
  }

  protected abstract void work();
}
