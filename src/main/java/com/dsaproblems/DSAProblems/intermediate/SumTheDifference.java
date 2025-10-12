package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SumTheDifference {

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        System.out.println(sumTheDifferencev1(A));

        List<Integer> input = new ArrayList<>(List.of(3, 5, 10));
        System.out.println(sumTheDifferencev2(input));
        System.out.println(sumTheDifferencev3(input));
    }

    private static int sumTheDifferencev3(List<Integer> input) {
        int len = input.size();
        Collections.sort(input);
        int mod = 1000000007;
        double max = 0, min = 0;
        for (int i = 0; i < len; i++) {
            double maxContribution = (input.get(i) % mod * Math.pow(2, i) % mod) % mod;
            double minContribution = (input.get(i) % mod * Math.pow(2, len - 1 - i) % mod) % mod;
            max = (max % mod + maxContribution % mod) % mod;
            min = (min % mod + minContribution % mod) % mod;
        }
        double diff = (max % mod - min % mod + mod) % mod;
        return (int) diff;
    }

    //working but throws TLE
    private static int sumTheDifferencev2(List<Integer> input) {
        int n = input.size();
        int possibilities = 1 << n;
        int sum = 0;
        int mod = 1000000007;
        for (int i = possibilities - 1; i >= 1; i--) {
            int min = 1000;
            int max = 1;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    min = Math.min(min, input.get(j));
                    max = Math.max(max, input.get(j));
                }
            }
            sum += (max % mod - min % mod + mod) % mod;
        }
        return sum;
    }

    private static int sumTheDifferencev1(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        double max = 0, min = 0;
        //using contribution technique and drived formula = A[i]*(2^i-2^n-1-i)
        int mod = 1000000007;
        for (int i = 0; i < n; i++) {
            double maxContribution = A[i] * ((Math.pow(2, i)) % mod);
            double minContribution = A[i] * ((Math.pow(2, n - 1 - i)) % mod);
            max += maxContribution % mod;
            min += minContribution % mod;
        }
        double diff = (max % mod - min % mod + mod) % mod;
        return (int) diff;
    }
}
