package ru.nsu.fit.apotapova;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BfsIterator used for iterating through a tree.
 *
 * @param <T> type of tree value
 */
public class BfsIterator<T> implements Iterator<Node<T>> {

  private final Node<T> root;
  private final Queue<Node<T>> queue = new LinkedList();

  public BfsIterator(Node<T> root) {
    this.root = root;
    queue.add(this.root);
  }

  @Override
  public boolean hasNext() {
    return !(queue.isEmpty());
  }

  @Override
  public Node<T> next() {
    if (hasNext()){
      Node<T> node = queue.poll();
      queue.addAll(node.getChildren());
      return node;
    }
    else {
      throw new RuntimeException("There are no nodes left");
    }
  }
  /*
  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    Iterator.super.forEachRemaining(action);
  }*/
}
