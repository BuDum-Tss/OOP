package ru.nsu.fit.apotapova.heapsort;

import org.jetbrains.annotations.NotNull;

/** Class HeapSort contains a method for sorting an array. */
public class HeapSort {
  /**
   *  Method heapSort sorts the array.
   *  
   * @param array - passes an array and returns a sorted array.
   */
  public static void heapSort(int @NotNull [] array) {
    int n = array.length;
    int i;
    for (i = n / 2 - 1; i >= 0; --i) {
      heapify(array, n, i);
    }
    for (i = n - 1; i > 0; --i) {
      int temp = array[0];
      array[0] = array[i];
      array[i] = temp;
      heapify(array, i, 0);
    }
  }

  private static void heapify(int[] array, int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < n && array[l] > array[i]) {
      largest = l;
    }
    if (r < n && array[r] > array[largest]) {
      largest = r;
    }
    if (largest != i) {
      int swap = array[i];
      array[i] = array[largest];
      array[largest] = swap;
      heapify(array, n, largest);
    }
  }
}
