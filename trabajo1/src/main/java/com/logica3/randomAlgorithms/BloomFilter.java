package com.logica3.randomAlgorithms;

import java.util.BitSet;

public class BloomFilter {
    private final BitSet bitSet;
    private final int size;
    private final int hashCount;

    public BloomFilter(int size, int hashCount) {
        this.size = size;
        this.hashCount = hashCount;
        this.bitSet = new BitSet(size);  // Use BitSet for the bit array
    }

    private int[] hashes(String item) {
        int[] hashResults = new int[hashCount];
        byte[] bytes = item.getBytes();  // Convert item to bytes

        for (int i = 0; i < hashCount; i++) {
            int hash = item.hashCode();  // Java's hashCode() method on the string
            // Simulate different hash functions by adding an index and taking modulo the size
            int hashResult = (hash + i) % size;
            hashResults[i] = hashResult;
        }
        return hashResults;
    }

    public void add(String item) {
        for (int hashVal : hashes(item)) {
            bitSet.set(hashVal);  // Set the bit at the position of each hash result to 1
        }
    }

    public boolean check(String item) {
        for (int hashVal : hashes(item)) {
            if (!bitSet.get(hashVal)) {
                // If any bit corresponding to the hash results is 0, the item is not in the set
                return false;
            }
        }
        // If all bits are set to 1, the item might be in the set (with some probability of false positive)
        return true;
    }

    public static void main(String[] args) {
        // Example usage
        BloomFilter bf = new BloomFilter(10, 3);

        // Add some email addresses
        String[] emails = {"test@example.com", "hello@world.com", "foo@bar.com"};
        for (String email : emails) {
            bf.add(email);  // Add email addresses to the Bloom filter
        }

        // Check if an email address is in the Bloom Filter
        String[] testEmails = {"test@example.com", "not_in_set@example.com"};
        for (String email : testEmails) {
            if (bf.check(email)) {
                System.out.println("'" + email + "' is possibly in the set.");  // Email might be in the set
            } else {
                System.out.println("'" + email + "' is definitely not in the set.");  // Email is not in the set
            }
        }
    }
}
