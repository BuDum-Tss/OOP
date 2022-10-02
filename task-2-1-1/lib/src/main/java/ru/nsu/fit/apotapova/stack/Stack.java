package ru.nsu.fit.apotapova.stack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/** Class Stack contains methods for working with this data storage method
 *
 * @param <T> - data type
 */
public class Stack<T> {
  int size = 32;
  private final T[] stack = (T[]) new Object[size];
  private int top = -1;

  /** add an element to the stack
   *
   * @param element - element
   */
  public void push(T element) {
    stack[++top] = element;
  }

  /** add collection of elements to the stack
   *
   * @param elements - collection of elements
   */
  public void pushStack(@NotNull Collection<T> elements) {
    int n = elements.size();
    T[] array = (T[]) elements.toArray();
    while (n-- > 0) stack[++top] = array[n];
  }

  /** take an element from the stack
   *
   * @return - element
   */
  public T pop() {
    return stack[top--];
  }

  /** take n elements from the stack
   *
   * @param n -  number of elements
   * @return - stack of elements
   */
  public Stack<T> popStack(int n) {
    Stack<T> container = new Stack<>();
    while (n-- > 0) {
      container.stack[++container.top] = stack[top--];
    }
    return container;
  }

  /** count the number of elements in the stack
   *
   * @return quantity of elements
   */
  public int count() {
    return top + 1;
  }

  /** converts stack to list
   *
   * @return List
   */
  public List<T> toList() {
    List<T> ans = (List<T>) new ArrayList<T>(top);
    ans.addAll(Arrays.asList(stack).subList(0, top + 1));
    return ans;
  }
}
