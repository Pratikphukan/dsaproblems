package com.dsaproblems.DSAProblems.dp02;

import java.util.Arrays;

public class WaysToReachNthStair {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(totalWaysof1or2v1(n));

        int[] arr = new int[n + 1];
        Arrays.fill(arr, -1);
        System.out.println(totalWaysof1or2v2(arr, n));

    }

    private static int totalWaysof1or2v2(int[] arr, int n) {
        if (arr[n] == -1) {
            if (n <= 2) {
                arr[n] = n;
                return n;
            }
            arr[n] = totalWaysof1or2v2(arr, n - 1) + totalWaysof1or2v2(arr, n - 2);
        }
        return arr[n];
    }

    private static int totalWaysof1or2v1(int n) {
        if (n <= 2) {
            return n;
        }
        return totalWaysof1or2v1(n - 1) + totalWaysof1or2v1(n - 2);
    }
}
