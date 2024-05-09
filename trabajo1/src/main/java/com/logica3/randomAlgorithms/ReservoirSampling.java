package com.logica3.randomAlgorithms;
import java.util.Iterator;
import java.util.Random;

public class ReservoirSampling {
    public static int[] reservoirSampling(Iterable<Integer> stream, int m) {
        Random rand = new Random();
        int[] reservoir = new int[m];
        int i = 0;

        // Initialize the reservoir with the first m elements from the stream
        for (int item : stream) {
            if (i < m) {
                reservoir[i] = item;
            } else {
                // Generate a random number from 0 to i (inclusive)
                int j = rand.nextInt(i + 1);

                // If the random number is within the range of the reservoir size
                if (j < m) {
                    reservoir[j] = item;
                }
            }
            i++;
        }

        return reservoir;
    }

    public static void main(String[] args) {
        // Example usage:
        // Let's say we have a stream of numbers from 1 to 10000 and we want to sample 20 of them
        int sampleSize = 20;
        Iterable<Integer> numberStream = () -> new Iterator<Integer>() {
            private int current = 1;

            @Override
            public boolean hasNext() {
                int max = 10000;
                return current <= max;
            }

            @Override
            public Integer next() {
                return current++;
            }
        };

        int[] sample = reservoirSampling(numberStream, sampleSize);
        for (int num : sample) {
            System.out.println(num);
        }
    }
}
