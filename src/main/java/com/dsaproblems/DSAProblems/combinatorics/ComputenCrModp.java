package com.dsaproblems.DSAProblems.combinatorics;

public class ComputenCrModp {
    public static void main(String[] args) {
        int A = 5;
        int B = 2;
        int C = 13;
        System.out.println(compute_nCr_mod_pv1(A, B, C));
        System.out.println(compute_nCr_mod_pv2(A, B, C));
    }

    private static int compute_nCr_mod_pv2(int A, int B, int C) {
        if (B < 0 || B > A) return 0;
        long[] fact = new long[A + 1];
        long[] invFact = new long[A + 1];
        fact[0] = 1;
        for (int i = 1; i <= A; i++) fact[i] = (fact[i - 1] * i) % C;
        invFact[A] = modPow(fact[A], C - 2, C);
        for (int i = A - 1; i >= 0; i--) invFact[i] = (invFact[i + 1] * (i + 1)) % C;
        long ans = fact[A] * invFact[B] % C * invFact[A - B] % C;
        return (int) ans;
    }

    private static long modPow(long a, long n, long m) {
        long res = 1;
        a %= m;
        while (n > 0) {
            if ((n & 1) == 1) res = (res * a) % m;
            a = (a * a) % m;
            n >>= 1;
        }
        return res;
    }

    private static int compute_nCr_mod_pv1(int A, int B, int C) {
        long nfact = factorial(A, C);
        long nrfact = factorial(A - B, C);
        long rfact = factorial(B, C);
        long nrpow = powFunction(nrfact, C - 2, C);
        long rpow = powFunction(rfact, C - 2, C);
        long ans = (((nfact % C) * (nrpow % C)) % C * (rpow % C)) % C;
        return (int) ans;
    }

    private static int factorial(long n, long m) {
        long product = 1;
        for (int i = 2; i <= n; i++) product = (product * i % m) % m;
        return (int) product;

    }

    private static int powFunction(long a, long n, long m) {
        if (n == 0) return 1;
        long he = powFunction(a, n / 2, m) % m;
        long ha = (he * he) % m;
        if (n % 2 == 0) {
            return (int) ha;
        } else {
            return (int) ((a * ha) % m);
        }
    }
}
