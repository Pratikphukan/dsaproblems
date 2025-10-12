package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeSubsequences {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 4, 5, 6));
        System.out.println(getPrimeSubsequencesv1(A));
    }

    private static int getPrimeSubsequencesv1(ArrayList<Integer> A) {
        int MOD = 1000000007;
        int primeCount = 0;
        for (int num : A) {
            if (isPrime(num)) {
                primeCount++;
            }
        }
        if (primeCount == 0) return 0;
        // Calculate 2^primeCount % MOD using modExp function.
        long totalSubsequences = modExp(2, primeCount);
        // Subtract 1 to remove the empty subset, ensuring non-negativity with MOD
        long result = (totalSubsequences - 1 + MOD) % MOD;
        return (int) result;
    }

    private static long modExp(long base, int exp) {
        int MOD = 1000000007;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        //Check divisibility from 2 to square root of n
        for (int i = 2; i * i <= num; i++) {
            // If n is divisible by i, then n isn't prime.
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
