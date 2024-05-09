package com.logica3.randomAlgorithms;

public class QuickSort {

    public static void deterministicQuicksort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            deterministicQuicksort(arr, low, p - 1);
            deterministicQuicksort(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
