package com.logica3;

public class RandomAlgorithms {

    public int hash1(int key){
        return (key % 100) + 1;
    }

    public int hash2(int key){
        return ((key * 11) % 100) + 1;
    }

    public int hash3(int key){
        return ((key * 31) % 100) + 1;
    }

    public void ordering(int[] arr){
        deterministicQuickSort(arr, 0, arr.length - 1);
    }

    private void deterministicQuickSort(int[] arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);
            deterministicQuickSort(arr, low, pi - 1);
            deterministicQuickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++){
            if (arr[j] < pivot){
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
}
