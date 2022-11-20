package ru.nsu.fit.apotapova;

import java.util.Iterator;
import java.util.Stack;

public class DFSIterator<T> implements Iterator<Node<T>> {
  private Node<T> root;
  private Stack<Node<T>> stack = new Stack<>();
  DFSIterator(Node<T> root)
  {
    this.root=root;
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
