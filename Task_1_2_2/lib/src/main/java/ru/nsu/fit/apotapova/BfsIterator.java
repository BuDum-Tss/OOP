package ru.nsu.fit.apotapova;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * BfsIterator used for iterating through a tree.
 *
 * @param <T> type of tree value
 */
public class BfsIterator<T> implements Iterator<Node<T>> {

  private final Queue<Node<T>> queue = new LinkedList();
  private final int numberOfChanges;


  /**
   * BfsIterator used for iterating through a tree.
   *
   * @param root root of the tree
   */
  public BfsIterator(Node<T> root) {
    queue.add(root);
    numberOfChanges = root.getNumberOfChanges();
  }

  /**
   * Checks if there is a next value to iterate through the tree.
   *
   * @return true/false
   */
  @Override
  public boolean hasNext() {
    boolean isEmpty = (queue.isEmpty());
    if (!(isEmpty) && numberOfChanges < queue.peek().getNumberOfChanges()) {
      throw new ConcurrentModificationException();
    }
    return !(isEmpty);
  }

  /**
   * Passes the next value to iterate through the tree.
   *
   * @return next node
   */
  @Override
  public Node<T> next() {
    if (hasNext()) {
      Node<T> node = queue.poll();
      queue.addAll(node.getChildren());
      return node;
    } else {
      throw new NoSuchElementException("There are no nodes left");
    }
  }
}
