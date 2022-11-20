package ru.nsu.fit.apotapova;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFSIterator<T> implements Iterator<Node<T>> {
  private final Node<T> root;
  private final Queue<Node<T>> queue = new LinkedList();
  public BFSIterator(Node<T> root) {
    this.root=root;
    queue.add(this.root);
  }
  @Override
  public boolean hasNext() {
    return !(queue.isEmpty());
  }
  @Override
  public Node<T> next() {
    queue.addAll(root.getChildren());
    return queue.poll();
  }
  /*
  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    Iterator.super.forEachRemaining(action);
  }*/
}
