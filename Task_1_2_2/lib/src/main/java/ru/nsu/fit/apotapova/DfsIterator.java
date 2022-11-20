package ru.nsu.fit.apotapova;

import java.util.Iterator;
import java.util.Stack;

/**
 * DFSIterator used for iterating through a tree.
 *
 * @param <T> type of tree value
 */
public class DfsIterator<T> implements Iterator<Node<T>> {

  private final Node<T> root;
  private final Stack<Node<T>> stack = new Stack<>();

  DfsIterator(Node<T> root) {
    this.root = root;
    stack.add(this.root);
  }

  @Override
  public boolean hasNext() {
    return !(stack.empty());
  }

  @Override
  public Node<T> next() {
    stack.addAll(root.getChildren());
    return stack.pop();
  }
  /*
  @Override
  public void forEachRemaining(Consumer<? super Node<T>> action) {
    Iterator.super.forEachRemaining(action);
  }*/
}
