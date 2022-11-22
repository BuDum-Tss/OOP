package ru.nsu.fit.apotapova;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * DFSIterator used for iterating through a tree.
 *
 * @param <T> type of tree value
 */
public class DfsIterator<T> implements Iterator<Node<T>> {

  private final Stack<Node<T>> stack = new Stack<>();
  private final int numberOfChanges;

  /**
   * DfsIterator used for iterating through a tree.
   *
   * @param root root of the tree
   */
  public DfsIterator(Node<T> root) {
    stack.add(root);
    numberOfChanges = root.getNumberOfChanges();
  }

  /**
   * Checks if there is a next value to iterate through the tree.
   *
   * @return true/false
   */
  @Override
  public boolean hasNext() {
    boolean isEmpty = stack.isEmpty();
    if (!(isEmpty) && numberOfChanges < stack.peek().getNumberOfChanges()) {
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
      Node<T> node = stack.pop();
      stack.addAll(node.getChildren());
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
