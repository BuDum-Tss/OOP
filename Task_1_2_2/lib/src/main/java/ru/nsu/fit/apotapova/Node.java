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
  public T value;
  private IteratorMode mode = null;
  private Node<T> parent;
  private int numberOfChanges = 0;

  public Node(T value) {
    this.value = value;
  }

  /**
   * Gets parent of node.
   *
   * @return parent of node
   */
  public Node<T> getParent() {
    return parent;
  }

  /**
   * Gets mode of iterator(BFS or DFS).
   *
   * @return iterator mode
   */
  public IteratorMode getMode() {
    return mode;
  }

  /**
   * Sets mode of iterator(BFS or DFS).
   *
   * @param mode iterator mode
   */
  public void setMode(IteratorMode mode) {
    this.mode = mode;
  }

  private void addChanges() {
    numberOfChanges++;
    if (parent == null) {
      return;
    }
    parent.addChanges();
  }

  /**
   * Creates a node with the value nodeValue and adds it from the root of the tree.
   *
   * @param nodeValue value of node
   * @return created node
   */
  public Node<T> add(T nodeValue) {
    Node<T> newNode = new Node<>(nodeValue);
    newNode.parent = this;
    newNode.setMode(mode);
    children.add(newNode);
    addChanges();
    return newNode;
  }

  /**
   * Adds a node to the tree and returns true, otherwise returns false.
   *
   * @param node local root
   */
  public void add(Node<T> node) throws Exception {
    List<Node<T>> ancestors = new ArrayList<>();
    Node<T> now = this;
    while (now != null) {
      ancestors.add(now);
      now = now.getParent();
    }
    boolean haveCycle = ancestors.contains(node);
    if (!haveCycle) {
      if (node.parent != null) {
        node.parent.children.remove(node);
      }
      node.parent = this;
      children.add(node);
      addChanges();
    } else {
      throw new Exception("Аttempt to create a loop in the tree");
    }
  }

  private List<Node<T>> getDescendants() {
    List<Node<T>> list = new ArrayList<>(this.children);
    this.children.forEach(child -> list.addAll(child.getDescendants()));
    return list;
  }

  /**
   * Removes node from list of root' children.
   *
   * @param node node being deleted
   * @return removed node
   */
  public Node<T> remove(Node<T> node) {
    children.remove(node);
    addChanges();
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
    if (mode == IteratorMode.DFS) {
      return new DfsIterator<>(this);
    }
    return new BfsIterator<>(this);
  }

  public int getNumberOfChanges() {
    return numberOfChanges;
  }
}