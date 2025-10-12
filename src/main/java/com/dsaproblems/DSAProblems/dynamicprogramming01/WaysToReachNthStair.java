package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.Arrays;

public class WaysToReachNthStair {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(totalWays(n));
        int[] arr = new int[n + 1];
        Arrays.fill(arr, -1);
        System.out.println(totalWays1(arr, n));
        System.out.println(totalWays2(n));
        System.out.println(totalWays3(n));

        System.out.println(totalWaysof1or2(n));

        System.out.println(totalWaysof1or2or3(n));

        System.out.println(totalWaysof1or2or3or4(n));

        System.out.println(alternateTotalWaysof1or2or3or4(n));
    }

    private static int totalWays3(int n) {
        int a = 1;
        int b = 1;
        int c = 2;
        for (int i = 3; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    private static int totalWays2(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }
        return arr[n];
    }

    private static int totalWays1(int[] arr, int n) {
        if (arr[n] == -1) {
            if (n == 0) {
                return 1;
            }
            if (n == 1 || n == 2) {
                return n;
            }
            arr[n] = totalWays1(arr, n - 1) + totalWays1(arr, n - 2) + totalWays1(arr, n - 3);
        }
        return arr[n];
    }

    // if there only two ways 1 and 2 steps: then the
    // series->1,1,2,3,5,....(fibonacci starting from 1)
    // if there are three ways 1, 2 and 3 stesp: then the
    // series->1,1,2,4,7,13,24,.....
    private static int totalWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return totalWays(n - 1) + totalWays(n - 2) + totalWays(n - 3);
    }

    // 2 ways->1 or 2 steps
    private static int totalWaysof1or2(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 2) {
            return n;
        }
        return totalWaysof1or2(n - 1) + totalWaysof1or2(n - 2);
    }

    // 3 ways->1 or 2 or 3 steps
    private static int totalWaysof1or2or3(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 3) {
            return n;
        }
        return totalWaysof1or2or3(n - 1) + totalWaysof1or2or3(n - 2) + totalWaysof1or2or3(n - 3);
    }

    // 4 ways->1 or 2 or 3 or 4 steps
    private static int totalWaysof1or2or3or4(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 3) {
            return n;
        }
        if (n == 3) {
            return n + 1;
        }
        return totalWaysof1or2or3or4(n - 1) + totalWaysof1or2or3or4(n - 2) + totalWaysof1or2or3or4(n - 3)
                + totalWaysof1or2or3or4(n - 4);
    }

    private static int alternateTotalWaysof1or2or3or4(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 4) {
            Double ways = Math.pow(2, n - 1);
            return ways.intValue();
        }
        return alternateTotalWaysof1or2or3or4(n - 1) + alternateTotalWaysof1or2or3or4(n - 2)
                + alternateTotalWaysof1or2or3or4(n - 3) + alternateTotalWaysof1or2or3or4(n - 4);
    }

}
