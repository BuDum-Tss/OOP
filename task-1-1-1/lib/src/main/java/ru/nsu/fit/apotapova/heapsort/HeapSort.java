//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package ru.nsu.fit.apotapova.heapsort;

public class HeapSort {
    public static void sortArr(int[] arr) {
        int n = arr.length;

        int i;
        for(i = n / 2 - 1; i >= 0; --i) {
            heapify(arr, n, i);
        }

        for(i = n - 1; i > 0; --i) {
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

    public static void printArr(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
