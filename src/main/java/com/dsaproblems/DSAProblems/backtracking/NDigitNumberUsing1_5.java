package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NDigitNumberUsing1_5 {

    public static void main(String[] args) {
        int n = 3;
        int[] currList = new int[n];
        List<Integer> ans = new ArrayList<>();
        findNDigitNumbersUsing1_5v1(n, 0, currList, ans);
        System.out.println(ans);
        ans = new ArrayList<>();
        findNDigitNumbersUsing1_5v2(n, 0, currList, ans);
        System.out.println(ans);
    }

    private static void findNDigitNumbersUsing1_5v2(int n, int idx, int[] currList, List<Integer> ans) {
        if (idx == n) {
            int num = 0;
            int multiplier = 1;
            for (int i = n - 1; i >= 0; i--) {
                num += currList[i] * multiplier;
                multiplier *= 10;
            }
            ans.add(num);
            return;
        }
        for (int i = 1; i <= 5; i++) {
            currList[idx] = i;
            findNDigitNumbersUsing1_5v1(n, idx + 1, currList, ans);
        }
    }

    private static void findNDigitNumbersUsing1_5v1(int n, int idx, int[] currList, List<Integer> ans) {
        if (idx == n) {
            int num = 0;
            int multiplier = 1;
            for (int i = n - 1; i >= 0; i--) {
                num += currList[i] * multiplier;
                multiplier *= 10;
            }
            ans.add(num);
            return;
        }
        currList[idx] = 1;
        findNDigitNumbersUsing1_5v1(n, idx + 1, currList, ans);
        currList[idx] = 2;
        findNDigitNumbersUsing1_5v1(n, idx + 1, currList, ans);
        currList[idx] = 3;
        findNDigitNumbersUsing1_5v1(n, idx + 1, currList, ans);
        currList[idx] = 4;
        findNDigitNumbersUsing1_5v1(n, idx + 1, currList, ans);
        currList[idx] = 5;
        findNDigitNumbersUsing1_5v1(n, idx + 1, currList, ans);
    }
}
