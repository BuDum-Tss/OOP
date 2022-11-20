package ru.nsu.fit.apotapova;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Node implements the tree data type.
 *
 * @param <T> type of data
 */
public class Node<T> implements Iterable<Node<T>> {

  private final List<Node<T>> children = new ArrayList<>();
  private Node.IteratorMode mode = null;
  private Node<T> root;
  private T value;

  Node(T value) {
    this.value = value;
  }

  public Node.IteratorMode getMode() {
    return mode;
  }

  /**
   * Sets mode of iterator(BFS or DFS).
   *
   * @param mode iterator mode
   */
  public void setMode(Node.IteratorMode mode) {
    this.mode = mode;
  }

  /**
   * Creates a node with the value nodeValue and adds it from the root of the tree.
   *
   * @param nodeValue value of node
   * @return created node
   */
  public Node<T> add(T nodeValue) {
    Node<T> newNode = new Node<>(nodeValue);
    newNode.setMode(mode);
    children.add(newNode);
    return newNode;
  }

  /**
   * Creates a node with the value nodeValue and adds it from the local root of the tree.
   *
   * @param localRoot local root
   * @param nodeValue value of node
   * @return created node
   */
  public Node<T> add(Node<T> localRoot, T nodeValue) {
    Node<T> newNode = new Node<>(nodeValue);
    newNode.setMode(mode);
    localRoot.children.add(newNode);
    return newNode;
  }

  /**
   * Removes node from list of root' children.
   *
   * @param node node being deleted
   * @return removed node
   */
  public Node<T> remove(Node<T> node) {
    children.remove(node);
    return node;
  }

  /**
   * Removes node from list of localRoot' children.
   *
   * @param localRoot the node whose child will be deleted
   * @param node      node being deleted
   * @return removed node
   */
  public Node<T> remove(Node<T> localRoot, Node<T> node) {
    localRoot.children.remove(node);
    return node;
  }

  /**
   * Returns list of children of this root.
   *
   * @return list of children of root
   */
  public List<Node<T>> getChildren() {
    return children;
  }

  /**
   * Returns iterator.
   *
   * @return iterator
   */
  @Override
  public Iterator<Node<T>> iterator() {
    switch (mode) {
      case BFS: {
        return new BfsIterator<>(this);
      }
      case DFS: {
        return new DfsIterator<>(this);
      }
      default: {
        return null;
      }
    }
  }

  public T getValue() {
    return value;
  }

  enum IteratorMode {
    BFS,
    DFS
  }
}
