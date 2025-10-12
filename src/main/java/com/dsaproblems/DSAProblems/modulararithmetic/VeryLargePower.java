package com.dsaproblems.DSAProblems.modulararithmetic;

public class VeryLargePower {

    public static void main(String[] args) {
        System.out.println(modularExponentiationv1(3, 4));
    }

    private static int modularExponentiationv1(int A, int B) {
        int mod = 1000000007;
        long fact = 1;
        for (long i = 2; i <= B; i++) {
            fact = (fact * i) % mod;
        }
        return (int) fastPow(A, fact, mod);
    }


    public static long fastPow(long A, long p, int mod) {
        if (p == 0)
            return 1;

        long half = fastPow(A, p / 2, mod) % mod;

        if (p % 2 == 0) {
            return (half * half) % mod;
        } else {
            return (((A * half) % mod) * half) % mod;
        }
    }
}
