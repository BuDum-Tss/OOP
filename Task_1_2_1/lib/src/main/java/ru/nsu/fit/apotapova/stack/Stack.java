package ru.nsu.fit.apotapova.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Class Stack contains methods for working with this data storage method.
 *
 * @param <T> - data type
 */

public class Stack<T> {

  int size = 32;
  private T[] stack = (T[]) new Object[size];
  private int top = -1;

  private void resize() {
    T[] newStack = (T[]) new Object[size * 2];
    System.arraycopy(stack, 0, newStack, 0, size);
    size *= 2;
  }

  /**
   * add an element to the stack.
   *
   * @param element - element
   */
  public void push(T element) {
    if (size - 1 == top) {
      resize();
    }
    stack[++top] = element;
  }

  /**
   * add elements from stack to the stack.
   *
   * @param elements - stack of elements
   */
  public void pushStack(@NotNull Stack<T> elements) {
    if (size - 1 == top) {
      resize();
    }
    int n = elements.count();
    for (int i = 0; i < n; i++) {
      stack[++top] = elements.stack[i];
    }
  }

  /**
   * take an element from the stack.
   *
   * @return - element
   */
  public T pop() {
    if (top == -1) {
      throw new ArrayIndexOutOfBoundsException("Out of bounds");
    }
    return stack[top--];
  }

  /**
   * take n elements from the stack.
   *
   * @param n - number of elements
   * @return - stack of elements
   */
  public Stack<T> popStack(int n) {
    if (n > top + 1) {
      throw new ArrayIndexOutOfBoundsException("Out of bounds");
    }
    Stack<T> container = new Stack<>();
    for (int i = 0; i < n; i++) {
      container.stack[i] = stack[top - n + 1 + i];
    }
    top -= n;
    container.top += n;
    return container;
  }

  /**
   * count the number of elements in the stack.
   *
   * @return quantity of elements
   */
  public int count() {
    return top + 1;
  }
}
