package ru.nsu.fit.apotapova;

import static ru.nsu.fit.apotapova.IteratorMode.BFS;
import static ru.nsu.fit.apotapova.IteratorMode.DFS;

import java.util.ConcurrentModificationException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NodeTest {

  @Test
  void testGetMode() {
    Node<Integer> root = new Node<>(0);
    Assertions.assertNull(root.getMode());
    root.setMode(BFS);
    Assertions.assertEquals(BFS, root.getMode());
  }

  @Test
  void testSetMode() {
    Node<Integer> root = new Node<>(0);
    Assertions.assertNull(root.getMode());
    root.setMode(DFS);
    Assertions.assertEquals(DFS, root.getMode());
  }

  @Test
  void testAdd() {
    Node<Integer> root = new Node<>(1);
    root.setMode(BFS);
    java.util.Iterator<Node<Integer>> iterator;
    root.add(11);
    Node<Integer> node = root.add(12);
    node.add(121);
    node = node.add(122);
    node.add(1221);
    /*
                   1
                /      \
              11        12
                      /    \
                    121    122
                              \
                             1221
     */
    iterator = root.iterator();
    Assertions.assertEquals(1, iterator.next().value);
    Assertions.assertEquals(11, iterator.next().value);
    Assertions.assertEquals(12, iterator.next().value);
    Assertions.assertEquals(121, iterator.next().value);
    Assertions.assertEquals(122, iterator.next().value);
    Assertions.assertEquals(1221, iterator.next().value);
  }

  @Test
  void testAdd2() throws Exception {
    Node<Integer> root = new Node<>(1);
    root.setMode(BFS);
    java.util.Iterator<Node<Integer>> iterator;
    root.add(11);
    Node<Integer> node = root.add(12);
    node.add(121);
    Node<Integer> node2 = new Node<>(122);
    node2.add(1221);
    node.add(node2);
    Assertions.assertThrows(java.util.ConcurrentModificationException.class, () -> node.add(node));
    /*
                   1
                /      \
              11        12
                      /    \
                    121    122
                              \
                             1221
     */
    iterator = root.iterator();
    Assertions.assertEquals(1, iterator.next().value);
    Assertions.assertEquals(11, iterator.next().value);
    Assertions.assertEquals(12, iterator.next().value);
    Assertions.assertEquals(121, iterator.next().value);
    Assertions.assertEquals(122, iterator.next().value);
    Assertions.assertEquals(1221, iterator.next().value);
  }

  @Test
  void testRemove() {
    Node<Integer> root = new Node<>(1);
    root.setMode(DFS);
    java.util.Iterator<Node<Integer>> iterator;
    root.add(11);
    Node<Integer> node = root.add(12);
    node.add(121);
    node = node.add(122);
    node.add(1221);
    iterator = root.iterator();
    Assertions.assertEquals(1, root.remove(iterator.next()).value);
    Assertions.assertEquals(12, root.remove(iterator.next()).value);
    Assertions.assertEquals(122, root.remove(iterator.next()).value);
    Assertions.assertEquals(1221, root.remove(iterator.next()).value);
    Assertions.assertEquals(121, root.remove(iterator.next()).value);
    Assertions.assertEquals(11, root.remove(iterator.next()).value);
  }

  @Test
  void testGetChildren() {
    Node<Integer> root = new Node<>(0);
    root.setMode(BFS);
    root.add(1);
    Assertions.assertEquals(1, root.getChildren().get(0).value);
  }

  @Test
  void testIterator() {
    Node<Integer> root = new Node<>(1);
    root.setMode(BFS);
    java.util.Iterator<Node<Integer>> iterator = root.iterator();
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(1, iterator.next().value);
    Assertions.assertFalse(iterator.hasNext());
  }

  @Test
  void testBfsIterator() {
    Node<Integer> root = new Node<>(1);
    root.setMode(BFS);
    Node<Integer> node1 = root.add(11);
    Node<Integer> node2 = root.add(12);
    node1.add(111);
    node1.add(112);
    node1 = node2.add(121);
    node2.add(122);
    node1.add(1211);
    node1.add(1212);
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
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(1, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(11, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(12, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(111, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(112, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(121, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(122, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(1211, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(1212, iterator.next().value);
    Assertions.assertFalse(iterator.hasNext());
  }

  @Test
  void testDfsIterator() {
    Node<Integer> root = new Node<>(1);
    root.setMode(DFS);
    Node<Integer> node1 = root.add(11);
    Node<Integer> node2 = root.add(12);
    node1.add(111);
    node1.add(112);
    node1 = node2.add(121);
    node2.add(122);
    node1.add(1211);
    node1.add(1212);
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
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(1, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(12, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(122, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(121, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(1212, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(1211, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(11, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(112, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(111, iterator.next().value);
    Assertions.assertFalse(iterator.hasNext());
  }

  @Test
  void testStream() {
    Node<Integer> root = new Node<>(1);
    root.setMode(DFS);
    Node<Integer> node1 = root.add(11);
    Node<Integer> node2 = root.add(12);
    node1.add(111);
    node1.add(112);
    node1 = node2.add(121);
    node2.add(122);
    node1.add(1211);
    node1.add(1212);
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
    Stream<Node<Integer>> stream = root.stream();
    stream.forEach(node -> node.value = node.value + 100000);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(100001, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(100012, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(100122, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(100121, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(101212, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(101211, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(100011, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(100112, iterator.next().value);
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(100111, iterator.next().value);
    Assertions.assertFalse(iterator.hasNext());
    stream = root.stream();
    Assertions.assertEquals(9, stream.count());
  }
}