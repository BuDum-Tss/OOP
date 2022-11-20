package ru.nsu.fit.apotapova;

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

  private final Node<T> root;
  private final Queue<Node<T>> queue = new LinkedList();
  private int numberOfChanges;
  public BfsIterator(Node<T> root) {
    this.root = root;
    queue.add(this.root);
    numberOfChanges = root.getNumberOfChanges();
  }

  @Override
  public boolean hasNext() {
    boolean isEmpty=(queue.isEmpty());
    if (!(isEmpty) && numberOfChanges<queue.peek().getNumberOfChanges())
      throw new ConcurrentModificationException();
    return !(isEmpty);
  }

  @Override
  public Node<T> next() {
    if (hasNext()){
      Node<T> node = queue.poll();
      queue.addAll(node.getChildren());
      return node;
    }
    else {
      throw new NoSuchElementException("There are no nodes left");
    }
  }
  @Override
  public void forEachRemaining(Consumer<? super Node<T>> action) {

    Iterator.super.forEachRemaining(action);
  }
}
