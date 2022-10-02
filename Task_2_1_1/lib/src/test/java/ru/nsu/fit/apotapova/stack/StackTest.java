package ru.nsu.fit.apotapova.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class StackTest {
  Stack<Integer> newStack = new Stack<>();

  @Test
  void testPush() {
    Integer x1 = 1;
    Integer x2 = 2;
    newStack.push(x1);
    newStack.push(x2);

    Integer[] arr = new Integer[] {5, 10, 3, 6};
    List<Integer> receivedList = new ArrayList<>(4);
    receivedList.addAll(Arrays.asList(arr).subList(0, 4));
    newStack.pushStack(receivedList);
    Assertions.assertEquals(newStack.count(), 6);
    Stack<Integer> expectedStack = newStack.popStack(4);
    List<Integer> expectedList = expectedStack.toList();

    Assertions.assertEquals(newStack.count(), 2);
    Assertions.assertArrayEquals(new List[] {expectedList}, new List[] {receivedList});
    Assertions.assertEquals(newStack.pop(), x2);
    Assertions.assertEquals(newStack.pop(), x1);
  }
}
