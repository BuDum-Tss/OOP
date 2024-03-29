package ru.nsu.fit.apotapova.heapsort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static ru.nsu.fit.apotapova.heapsort.HeapSort.heapSort;

class HeapSortTest {
  @Test
  void heapsort() {
    {
      int[] array = {3, 5, 1, 4, 2};
      int[] answer = {1, 2, 3, 4, 5};
      int k = array.length;
      heapSort(array);
      Assertions.assertArrayEquals(answer,array);
    }
    {
      int[] array = {1, 2, 3, 4, 5};
      int[] answer = {1, 2, 3, 4, 5};
      int k = array.length;
      heapSort(array);
      Assertions.assertArrayEquals(answer,array);
    }
    {
      int[] array = {5, 4, 3, 2, 1};
      int[] answer = {1, 2, 3, 4, 5};
      int k = array.length;
      heapSort(array);
      Assertions.assertArrayEquals(answer,array);
    }
    {
      int[] array = {-1, -1, -1, -1};
      int[] answer = {-1, -1, -1, -1};
      int k = array.length;
      heapSort(array);
      Assertions.assertArrayEquals(answer,array);
    }
    {
      int[] array = {-5, -3, -4, -2, -1};
      int[] answer = {-5, -4, -3, -2, -1};
      int k = array.length;
      heapSort(array);
      Assertions.assertArrayEquals(answer,array);
    }
    {
      int[] array = {};
      int[] answer = {};
      int k = array.length;
      heapSort(array);
      Assertions.assertArrayEquals(answer,array);
    }
  }
}
