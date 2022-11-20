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
    Node<Integer> root = new Node(1);
    root.setMode(BFS);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
    root.add(11);
    Node<Integer> node = root.add(12);
    root.add(node, 121);
    node = root.add(node, 122);
    root.add(node, 1221);
    iterator = root.iterator();
    Assertions.assertEquals(1, iterator.next().getValue());
    Assertions.assertEquals(11, iterator.next().getValue());
    Assertions.assertEquals(12, iterator.next().getValue());
    Assertions.assertEquals(121, iterator.next().getValue());
    Assertions.assertEquals(122, iterator.next().getValue());
    Assertions.assertEquals(1221, iterator.next().getValue());
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
    Node<Integer> root = new Node(1);
    root.setMode(DFS);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
    root.add(11);
    Node<Integer> node = root.add(12);
    root.add(node, 121);
    node = root.add(node, 122);
    root.add(node, 1221);
    iterator = root.iterator();
    Assertions.assertEquals(1, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(12, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(122, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(1221, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(121, root.remove(iterator.next()).getValue());
    Assertions.assertEquals(11, root.remove(iterator.next()).getValue());
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

  @Test
  void testBfsIterator() {
    Node<Integer> root = new Node(1);
    root.setMode(BFS);
    Node<Integer> node1 = root.add(11);
    Node<Integer> node2 = root.add(12);
    root.add(node1, 111);
    root.add(node1, 112);
    node1 = root.add(node2, 121);
    root.add(node2, 122);
    root.add(node1, 1211);
    root.add(node1, 1212);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
    /*
                  1
               /     \
            11         12
          /    \      /    \
        111    112  121     122
                   /   \
                1211    1212
     */
    Assertions.assertEquals(1, iterator.next().getValue());
    Assertions.assertEquals(11, iterator.next().getValue());
    Assertions.assertEquals(12, iterator.next().getValue());
    Assertions.assertEquals(111, iterator.next().getValue());
    Assertions.assertEquals(112, iterator.next().getValue());
    Assertions.assertEquals(121, iterator.next().getValue());
    Assertions.assertEquals(122, iterator.next().getValue());
    Assertions.assertEquals(1211, iterator.next().getValue());
    Assertions.assertEquals(1212, iterator.next().getValue());
  }

  @Test
  void testDfsIterator() {
    Node<Integer> root = new Node(1);
    root.setMode(DFS);
    Node<Integer> node1 = root.add(11);
    Node<Integer> node2 = root.add(12);
    root.add(node1, 111);
    root.add(node1, 112);
    node1 = root.add(node2, 121);
    root.add(node2, 122);
    root.add(node1, 1211);
    root.add(node1, 1212);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
    /*
                  1
               /     \
            11         12
          /    \      /    \
        111    112  121     122
                   /   \
                1211    1212
     */
    Assertions.assertEquals(1, iterator.next().getValue());
    Assertions.assertEquals(12, iterator.next().getValue());
    Assertions.assertEquals(122, iterator.next().getValue());
    Assertions.assertEquals(121, iterator.next().getValue());
    Assertions.assertEquals(1212, iterator.next().getValue());
    Assertions.assertEquals(1211, iterator.next().getValue());
    Assertions.assertEquals(11, iterator.next().getValue());
    Assertions.assertEquals(112, iterator.next().getValue());
    Assertions.assertEquals(111, iterator.next().getValue());

  }
}