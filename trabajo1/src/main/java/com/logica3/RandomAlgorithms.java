package com.logica3;
import com.clearspring.analytics.stream.cardinality.HyperLogLog;

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

    public long counting(int[] arr){
        return hyperLogLog(arr);
    }

    private long hyperLogLog(int[] arr){
        HyperLogLog hll = new HyperLogLog(14);
        for (int i = 0; i < arr.length; i++){
            hll.offer(arr[i]);
        }
        long estimatedDistinct = hll.cardinality();

        return estimatedDistinct;

        
    }
}
            
