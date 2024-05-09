package com.logica3.randomAlgorithms;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

public class HyperLogLog {
    // Define hash function to obtain a bit representation of every item to be counted
    public static String hashElement(String element) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(element.getBytes());
        byte[] digest = md.digest();
        // Convert the hash bytes to binary string
        BigInteger bigInt = new BigInteger(1, digest);
        return String.format("%256s", bigInt.toString(2)).replace(' ', '0'); // Ensure fixed length binary
    }

    // Calculate the position of the leftmost 1-bit
    public static int R(String x) {
        return x.indexOf('1');
    }

    // HyperLogLog algorithm
    public static double hyperloglog(Stream<String> stream, int b) throws NoSuchAlgorithmException {
        int m = 1 << b; // Number of registers, equivalent to 2^b
        double alpha;
        if (m > 64) {
            alpha = 0.7213 / (1 + 1.079 / m);
        } else {
            alpha = 0.673;
        }

        // Initialize the collection of registers C
        int[] C = new int[m];

        // Process each element in the stream
        stream.forEach(element -> {
            try {
                String X = hashElement(element); // Hash the element
                int j = Integer.parseInt(X.substring(0, b), 2); // Take the first b bits to find the register index
                int r = R(X.substring(b)); // Position of the first 1-bit in the remaining bits
                C[j] = Math.max(r, C[j]); // Update the register with the maximum number of leading zeros
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });

        // Calculate the harmonic mean of 2 raised to the power of the register values
        double harmonicMean = 0;
        for (int Cj : C) {
            harmonicMean += Math.pow(2, -Cj);
        }

        // Estimate the cardinality
        double E = alpha * Math.pow(m, 2) / harmonicMean;

        // Small range corrections can be added here if needed (not included in this simplified version)

        return E; // Estimated number of distinct elements
    }

    public static void main(String[] args) {
        // Example usage
        String[] items = {"apple", "banana", "apple", "orange", "banana", "grape", "pear", "grapefruit", "pear", "orange"};
        Stream<String> itemStream = Stream.of(items);

        // Parameter b defines the number of bits used for addressing registers, and hence m=2^b registers
        int b = 10;

        try {
            // Run the HyperLogLog algorithm
            double distinctElementsEstimate = hyperloglog(itemStream, b);
            System.out.printf("Estimated number of distinct items: %.0f\n", distinctElementsEstimate);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
