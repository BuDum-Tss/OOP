package ru.nsu.fit.apotapova;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class for notification of the status of orders.
 */
public class NotificationSystem implements Runnable {

  protected final static BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

  /**
   * Puts message in queue to right sequence of printing.
   *
   * @param message putted message
   */
  public static void newMessage(String message) {
    try {
      messageQueue.put(message);
    } catch (InterruptedException e) {
      throw new RuntimeException("Unexpected interruption waiting the queue");
    }
  }

  @Override
  public void run() {
    try {
      while (!(Thread.currentThread().isInterrupted() && messageQueue.isEmpty())) {
        String message;
        message = messageQueue.take();
        System.out.println(message);
      }
    } catch (InterruptedException ignored) {
    }
  }
}
