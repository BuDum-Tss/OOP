package ru.nsu.fit.apotapova.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackTest {

  Stack<Integer> test = new Stack<>();

  @Test
  void push() {
    test.push(1);
    test.push(2);
    test.push(3);
    Assertions.assertEquals(test.pop(), 3);
    Assertions.assertEquals(test.pop(), 2);
    Assertions.assertEquals(test.pop(), 1);

  }

  @Test
  void pushStack() {
    test.push(1);
    test.push(2);
    Stack<Integer> add = new Stack<>();
    add.push(3);
    add.push(4);
    test.pushStack(add);
    Assertions.assertEquals(test.pop(), 4);
    Assertions.assertEquals(test.pop(), 3);
    Assertions.assertEquals(test.pop(), 2);
    Assertions.assertEquals(test.pop(), 1);
  }

  @Test
  void pop() {
    test.push(1);
    Assertions.assertEquals(test.pop(),1);
    assertThrows(RuntimeException.class, () -> test.pop());
  }

  @Test
  void popStack() {
    Stack<Integer> poptest;
    test.push(1);
    test.push(2);
    test.push(3);
    test.push(4);
    poptest = test.popStack(2);
    Assertions.assertEquals(poptest.pop(), 4);
    Assertions.assertEquals(poptest.pop(), 3);
    assertThrows(RuntimeException.class, () -> test.popStack(3));
  }

  @Test
  void count() {
    test.push(1);
    test.push(2);
    test.push(3);
    Assertions.assertEquals(test.count(), 3);
  }
}