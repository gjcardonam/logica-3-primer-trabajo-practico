package com.logica3.randomAlgorithms;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.stream.Stream;

import static com.logica3.randomAlgorithms.HyperLogLog.hyperloglog;
import static com.logica3.randomAlgorithms.QuickSort.deterministicQuicksort;
import static com.logica3.randomAlgorithms.ReservoirSampling.reservoirSampling;

public class RandomAlgorithms {

    public void ordering(int[] arr){
        deterministicQuicksort(arr, 0, arr.length - 1);
    }

    public double counting(int[] arr) {
        String[] items = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            items[i] = Integer.toString(arr[i]);
        }
        Stream<String> itemStream = Stream.of(items);
        int b = 10;
        try {
            return hyperloglog(itemStream, b);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error en el conteo por QuickSort Deterministic: " + e.getMessage());
            return 0;
        }
    }

    public int[] sampling(int[] arr, int k){
        Iterable<Integer> numberStream = () -> new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < arr.length;
            }

            @Override
            public Integer next() {
                return arr[index++];
            }
        };
        return reservoirSampling(numberStream, k);
    }

    public boolean[] finding(int[] arr, int[] keys){
        BloomFilter bf = new BloomFilter(10000, 3);
        for (int key : keys) {
            bf.add(Integer.toString(key));
        }
        boolean[] results = new boolean[arr.length];
        for (int i : arr) {
            results[i] = bf.check(Integer.toString(i));
        }
        return results;
    }

    public int hash1(int key){
        return (key % 100) + 1;
    }

    public int hash2(int key){
        return ((key * 11) % 100) + 1;
    }

    public int hash3(int key){
        return ((key * 31) % 100) + 1;
    }
}
            
