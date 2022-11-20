package ru.nsu.fit.apotapova;

import static ru.nsu.fit.apotapova.Node.IteratorMode.BFS;
import static ru.nsu.fit.apotapova.Node.IteratorMode.DFS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NodeTest {

  @Test
  void testGetMode() {
    Node<Integer> root = new Node(0);
    Assertions.assertNull(root.getMode());
    root.setMode(BFS);
    Assertions.assertEquals(BFS, root.getMode());
  }

  @Test
  void testAdd() {
    Node<Integer> root = new Node(0);
    root.setMode(BFS);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
    root.add(1);
    root.add(2);
    root.add(3);
    Assertions.assertEquals(0, iterator.next().getValue());
    Assertions.assertEquals(1, iterator.next().getValue());
    Assertions.assertEquals(2, iterator.next().getValue());
    Assertions.assertEquals(3, iterator.next().getValue());
  }

  @Test
  void testSetMode() {
    Node<Integer> root = new Node(0);
    Assertions.assertNull(root.getMode());
    root.setMode(DFS);
    Assertions.assertEquals(DFS, root.getMode());
  }

  @Test
  void testRemove() {
    Node<Integer> root = new Node(0);
    root.setMode(BFS);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
    root.add(1);
    root.add(2);
    root.add(3);
    Assertions.assertEquals(0, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(1, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(2, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(3, root.remove(iterator.next()).getValue());
  }

  @Test
  void testGetChildren() {
    Node<Integer> root = new Node(0);
    root.setMode(BFS);
    root.add(1);
    Assertions.assertEquals(1, root.getChildren().get(0).getValue());
  }

  @Test
  void testIterator() {
    Node<Integer> root = new Node(0);
    root.setMode(BFS);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
  }
}