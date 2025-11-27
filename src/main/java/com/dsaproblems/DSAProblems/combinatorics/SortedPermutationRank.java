package com.dsaproblems.DSAProblems.combinatorics;

public class SortedPermutationRank {

    private static final int MOD = 1000003;

    public static void main(String[] args) {
        String A = "cab";
        System.out.println(findRankv1(A)); // Expected output: 2
    }

    private static int findRankv1(String A) {
        int n = A.length();
        long[] factorial = computeFactorials(n);
        long rank = 0L;
        char[] chars = A.toCharArray();
        for (int i = 0; i < n; i++) {
            int countSmaller = 0;
            for (int j = i + 1; j < n; j++) {
                // If any of these characters is lexicographically smaller than chars[i], increment the count
                if (chars[j] < chars[i]) countSmaller++;
            }
            rank = (rank + countSmaller * factorial[n - i - 1]) % MOD;
        }
        // The number of permutations that can be made with the remaining characters
        // is given by factorial of (n - i - 1)
        // Multiplying with countSmaller gives the number of permutations that lexicographically come before
        // the current permutation starting with chars[i]
        return (int) ((rank + 1) % MOD);
    }

    private static long[] computeFactorials(int n) {
        long[] factorial = new long[n + 1];
        factorial[0] = 1;// 0! is 1
        for (int i = 1; i <= n; i++) {
            // Each factorial is the multiplication of the previous factorial and i
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }
        return factorial;
    }
}
