package com.logica3;
import java.util.Random;

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
        for (int j : arr) {
            hll.offer(j);
        }

        return hll.cardinality();
    }

    public int[] sampling(int[] arr, int k){
        return reservoirSample(arr, k);
    }

    private int[] reservoirSample(int[] stream, int k) {
        int[] reservoir = new int[k];
        int i;
        Random rand = new Random();
        
        for (i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }
        
        for (; i < stream.length; i++) {
            int j = rand.nextInt(i + 1);
            
            if (j < k) {
                reservoir[j] = stream[i];
            }
        }
        return reservoir;
    }

    public boolean[] bloomFilter(int[] arr, int[] keys){
        boolean[] bloom = new boolean[1000];
        for (int key : keys){
            bloom[hash1(key)] = true;
            bloom[hash2(key)] = true;
            bloom[hash3(key)] = true;
        }
        boolean[] result = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++){
            result[i] = bloom[hash1(arr[i])] && bloom[hash2(arr[i])] && bloom[hash3(arr[i])];
        }
        return result;
    }
}
            
