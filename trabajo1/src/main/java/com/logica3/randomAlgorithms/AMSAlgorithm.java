package com.logica3.randomAlgorithms;

import java.util.List;
import java.util.Random;

public class AMSAlgorithm {
    public static int amsAlgorithm(List<String> stream) {
        Random rand = new Random();
        String randomItem = null;
        int n = 0; // Total number of items processed
        int i = 0;

        // Select a random item from the stream
        for (String item : stream) {
            n++;
            if (rand.nextInt(n) == n - 1) {
                randomItem = item;
            }
        }

        // If no item was selected, return 0
        if (randomItem == null) {
            return 0;
        }

        // Count the occurrences of the random item
        int countRandomItem = 0;
        for (String item : stream) {
            if (item.equals(randomItem)) {
                countRandomItem++;
            }
        }

        // Estimate the second moment

        return (countRandomItem - 1) * (n - 1);
    }

    public static void main(String[] args) {
        // Example usage
        List<String> stream = List.of("apple", "banana", "apple", "orange", "banana", "grape", "pear", "grapefruit", "pear", "orange");

        // Run the AMS algorithm
        int f2Estimate = amsAlgorithm(stream);
        System.out.println("Approximate second moment (F2): " + f2Estimate);
    }
}
