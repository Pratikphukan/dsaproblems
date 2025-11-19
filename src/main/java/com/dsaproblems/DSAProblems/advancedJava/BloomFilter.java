package com.dsaproblems.DSAProblems.advancedJava;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class BloomFilter {

    // Define the number of hash functions to simulate using double hashing
    private static final int NUM_HASHES = 5;

    // Define the size of the BitSet (prime for better hashing spread)
    private static final int SIZE = 100003;

    // Static BitSet instance that represents the Bloom Filter's bit array
    private static final BitSet bitSet = new BitSet(SIZE);

    private static final Set<Integer> tokenSet = new HashSet<>();


    static double ln2 = Math.log(2);
    // m = - (n * ln p) / (ln 2)^2 -> bit array size
    private static final int m = (int) Math.ceil(-(1000 * Math.log(0.05)) / (ln2 * ln2));
    private static final BitSet bits = new BitSet(m);
    // k = (m / n) * ln 2 -> // number of hash functions
    private static final int k = Math.max(1, (int) Math.round((m / (double) 1000) * ln2));

    public static void main(String[] args) {
        String testWord1 = "hello";
        insertv3(testWord1); // Insert "hello" into the Bloom Filter
        System.out.println(lookupv3(testWord1));

        String testWord2 = "world";
        // Expect lookup to return false (or rarely true due to false positives).
        System.out.println(lookupv3(testWord2));


        String testWord3 = "bloom";
        String testWord4 = "filter";
        insertv3(testWord3); // Insert "bloom"
        insertv3(testWord4); // Insert "filter"
        // Expect lookup should return true for both since they were inserted.
        System.out.println(lookupv3(testWord3));
        System.out.println(lookupv3(testWord4));


        String testWord5 = "blood";
        System.out.println(lookupv3(testWord5));
    }

    private static boolean lookupv3(String token) {
        int h1 = token.hashCode();
        int h2 = reverseHash(token);
        for (int i = 0; i < k; i++) {
            int combined = computeIndex(h1, h2, i);
            if (!bits.get(combined)) return false;
        }
        return true;
    }

    private static int reverseHash(String token) {
        return new StringBuilder(token).reverse().toString().hashCode();
    }

    private static void insertv3(String token) {
        int h1 = token.hashCode();
        int h2 = reverseHash(token);
        for (int i = 0; i < k; i++) {
            int combined = computeIndex(h1, h2, i);
            bits.set(combined);
        }
    }

    //0xffffffffL->16^8-1->4_294_967_295
    private static int computeIndex(int h1, int h2, int i) {
        long combined = (h1 & 0xffffffffL) + (i * (h2 & 0xffffffffL));
        int idx = (int) (Math.abs(combined) % m);
        return idx;
    }

    private static boolean lookupv1(String token) {
        for (int i = 0; i < NUM_HASHES; i++) {
            int idx = getHash(token, i);
            if (!bitSet.get(idx)) {
                return false; // Early return since at least one hash position is not set.
            }
        }
        // If all hash positions are set, return true indicating `s` might be present.
        return true;
    }

    private static void insertv1(String token) {
        for (int i = 0; i < NUM_HASHES; i++) {
            int idx = getHash(token, i);
            bitSet.set(idx);
        }
    }

    private static int getHash(String token, int i) {
        int hash1 = token.hashCode();
        int hash2 = new StringBuilder(token).reverse().toString().hashCode();
        int combinedHash = hash1 + i * hash2;
        return Math.abs(combinedHash % SIZE);
    }

    private static boolean lookupv2(String token) {
        return tokenSet.contains(computeHash(token));
    }

    private static void insertv2(String token) {
        int hash = computeHash(token);
        tokenSet.add(hash);
    }

    private static int computeHash(String token) {
        long mod = 1000000007;
        long hash = 0L;
        for (int i = 0; i < token.length(); i++) {
            char ch = token.charAt(i);
            int num = ch - 'a';
            long val = ((long) num % mod * (long) (Math.pow(26, i) % mod)) % mod;
            hash = (hash % mod + val % mod) % mod;
        }
        return (int) hash;
    }
}
