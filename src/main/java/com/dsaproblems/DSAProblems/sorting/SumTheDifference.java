package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SumTheDifference {

    public static void main(String[] args) {
        List<Integer> a = List.of(5, 4, 2);
        ArrayList<Integer> A = new ArrayList<>(a);
        System.out.println(sumTheDifference(A));
        System.out.println(sumTheDifference1(A));
        System.out.println(sumTheDifference2(A));
        System.out.println(sumTheDifferencev3(A));
    }

    //working code
    private static int sumTheDifferencev3(ArrayList<Integer> A) {
        Collections.sort(A);
        int MOD = 1_000_000_007;
        int n = A.size();
        long[] pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        long minus = 0, plus = 0;
        for (int i = 0; i < n; i++) {
            int val = A.get(i);
            plus = (plus + val * pow2[i] % MOD) % MOD;
            minus = (minus + val * pow2[n - 1 - i] % MOD) % MOD;
        }
        return (int) ((plus - minus + MOD) % MOD);
    }

    private static int sumTheDifference2(ArrayList<Integer> A) {
        Collections.sort(A);
        int Mod = 1000 * 1000 * 1000 + 7;
        long minus = 0, plus = 0;
        for (int i = 0; i < A.size(); i++) {
            int val = A.get(i);
            minus += (val * pow(2, A.size() - 1 - i, Mod)) % Mod; //It is the minimum in (2^{n-1-i}) subsets (all subsets formed by choosing any of the later (n-1-i) elements)
            minus %= Mod;
            plus += (val * pow(2, i, Mod)) % Mod; //It is the maximum in (2^i) subsets (all subsets formed by choosing any of the previous (i) elements)
            plus %= Mod;
        }
        return (int) ((plus - minus + Mod) % Mod);
    }

    private static long pow(long x, int y, int k) {
        long result = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                result = (result * x) % k;
                y--;
            }
            y >>= 1;
            x = (x * x) % k;
        }
        return result;
    }

    private static int sumTheDifference1(ArrayList<Integer> A) {
        Collections.sort(A);
        int MOD = 1000000007;
        int n = A.size();
        // iterate over array and with help of
        // horner's rule calc max_sum and min_sum
        long min_sum = 0, max_sum = 0;
        for (int i = 0; i < n; i++) {
            max_sum = 2 * max_sum + A.get(n - 1 - i);
            max_sum %= MOD;
            min_sum = 2 * min_sum + A.get(i);
            min_sum %= MOD;
        }

        return (int) (max_sum - min_sum + MOD) % MOD;
    }

    private static int sumTheDifference(ArrayList<Integer> A) {
        Collections.sort(A);
        int MOD = 1000000007;
        int fac = 1;
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < A.size(); i++) {
            sum1 += (A.get(i) * fac % MOD) % MOD;
            fac = (fac % MOD * 2) % MOD;
        }
        fac = fac >> 1;
        for (int i = 0; i < A.size(); i++) {
            sum2 += (A.get(i) * fac % MOD) % MOD;
            fac = fac >> 1;
        }
        return (int) (sum1 - sum2 + MOD) % MOD;
    }

}
