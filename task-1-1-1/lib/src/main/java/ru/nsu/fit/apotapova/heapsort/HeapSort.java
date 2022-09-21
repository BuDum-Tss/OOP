package ru.nsu.fit.apotapova.heapsort;

import org.jetbrains.annotations.NotNull;

/** Class HeapSort contains a method for sorting an array. */
public class HeapSort {
  /** Method heapsrt sorts the array. */
  public static void heapsrt(int @NotNull [] arr) {
    int n = arr.length;
    int i;
    for (i = n / 2 - 1; i >= 0; --i) {
      heapify(arr, n, i);
    }
    for (i = n - 1; i > 0; --i) {
      int tmp = arr[0];
      arr[0] = arr[i];
      arr[i] = tmp;
      heapify(arr, i, 0);
    }
  }

  private static void heapify(int[] arr, int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < n && arr[l] > arr[i]) {
      largest = l;
    }
    if (r < n && arr[r] > arr[largest]) {
      largest = r;
    }
    if (largest != i) {
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;
      heapify(arr, n, largest);
    }
  }
}
