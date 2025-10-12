package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MaximumMinimumMagic {

    public static void main(String[] args) {

        //3, 11, -1, 5
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(3, 11, -1, 5));
        System.out.println(findMaximumMinimumMagicv1(input));
        System.out.println(findMaximumMinimumMagicv2(input));
    }

    private static ArrayList<Integer> findMaximumMinimumMagicv2(ArrayList<Integer> A) {
        Collections.sort(A);
        int n = A.size();
        int MOD = 1000000007;
        long min = 0, max = 0;
        for (int i = 0; i < n / 2; i++) {
            max += Math.abs(A.get(i) - A.get(n - i - 1));
            min += Math.abs(A.get(2 * i) - A.get(2 * i + 1));
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add((int) (max % MOD));
        ans.add((int) (min % MOD));
        return ans;
    }

    public static ArrayList<Integer> findMaximumMinimumMagicv1(ArrayList<Integer> A) {
        Collections.sort(A);
        ArrayList<Integer> ans = new ArrayList<>();
        int MOD = 1000000007;
        long min = 0;
        for (int i = 0; i < A.size() - 1; i += 2) {
            min = min + Math.abs(A.get(i) - A.get(i + 1));
            min = min % MOD;
        }
        long max = 0;
        for (int i = 0; i < A.size() / 2; i++) {
            max = max + Math.abs(A.get(i) - A.get(A.size() - i - 1));
            max = max % MOD;
        }
        ans.add((int) max);
        ans.add((int) min);
        return ans;
    }
}
