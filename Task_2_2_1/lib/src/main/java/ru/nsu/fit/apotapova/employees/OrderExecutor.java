package ru.nsu.fit.apotapova.employees;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class OrderExecutor implements Runnable {

  private int id;
  private final AtomicBoolean isInterrupted = new AtomicBoolean(false);

  @Override
  public void run() {
    try {
      while (!isInterrupted.get()) {
        work();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException("Unexpected interruption");
    }
  }

  protected abstract void work() throws InterruptedException;

  public void interrupt() {
    isInterrupted.set(!isInterrupted.get());
  }
}
