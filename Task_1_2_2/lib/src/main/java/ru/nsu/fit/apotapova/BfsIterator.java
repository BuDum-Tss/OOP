package ru.nsu.fit.apotapova;

import static ru.nsu.fit.apotapova.IteratorMode.BFS;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Consumer;

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

  @Override
  public boolean hasNext() {
    boolean isEmpty = (queue.isEmpty());
    if (!(isEmpty) && numberOfChanges < queue.peek().getNumberOfChanges()) {
      throw new ConcurrentModificationException();
    }
    return !(isEmpty);
  }

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

  @Override
  public void forEachRemaining(Consumer<? super Node<T>> action) {

    Iterator.super.forEachRemaining(action);
  }
}
