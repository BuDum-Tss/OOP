package ru.nsu.fit.apotapova.heapsort;
// Assertions

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.fit.apotapova.heapsort.HeapSort.heapsrt;

class HeapSortTest {
  private static void test(int[] arr) {
    System.out.println("in:");
    for (int j : arr) {
      System.out.print(j + " ");
    }
    System.out.println();
    heapsrt(arr);
    System.out.println("out:");
    for (int j : arr) {
      System.out.print(j + " ");
    }
    System.out.print("\n\n");
  }

  @Test
  void heapsort() {
    {
      int[] arr = {3, 5, 1, 4, 2};
      test(arr);
    }
    {
      int[] arr = {1, 2, 3, 4, 5};
      test(arr);
    }
    {
      int[] arr = {5, 4, 3, 2, 1};
      test(arr);
    }
    {
      int[] arr = {-1, -1, -1, -1};
      test(arr);
    }
    {
      int[] arr = {-5, -3, -4, -2, -1};
      test(arr);
    }
    {
      int[] arr = {};
      test(arr);
    }
  }
}
