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

  public Node<T> getParent() {
    return parent;
  }

  private Node<T> parent;

  Node(T value) {
    this.value = value;
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

  /**
   * Creates a node with the value nodeValue and adds it from the root of the tree.
   *
   * @param nodeValue value of node
   * @return created node
   */
  public Node<T> add(T nodeValue) {
    Node<T> newNode = new Node<>(nodeValue);
    newNode.parent=this;
    newNode.setMode(mode);
    children.add(newNode);
    return newNode;
  }

  /**
   * Adds a node to the tree and returns true, otherwise returns false.
   *
   * @param node local root
   * @return is node added or not
   */
  public boolean add(Node<T> node) {
    List<Node<T>> ancestors = new ArrayList<>();
    List<Node<T>> descendants = getDescendants();
    Node<T> now=this;
    while (now!=null)
    {
      ancestors.add(now.getParent());
      now=now.getParent();
    }
    boolean haveCycle=false;
    now=node;
    Iterator<Node<T>> iterator = descendants.iterator();
    while (iterator.hasNext())
    {
      if (ancestors.contains(iterator.next()))
      {
        haveCycle=true;
        break;
      }
    }
    if (haveCycle)
      return false;
    children.add(node);
    return true;
  }
  private List<Node<T>> getDescendants(){
    List<Node<T>> list = new ArrayList<>();
    list.addAll(this.children);
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
}
