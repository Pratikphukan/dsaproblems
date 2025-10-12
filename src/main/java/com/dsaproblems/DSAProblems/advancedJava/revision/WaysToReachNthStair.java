package com.dsaproblems.DSAProblems.advancedJava.revision;

public class WaysToReachNthStair {

    public static void main(String[] args) {
        int n = 5;

        System.out.println(totalWaysof1or2or3or4(n));
    }

    // 4 ways->1 or 2 or 3 or 4 steps
    private static int totalWaysof1or2or3or4(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 4) {
            return 1 << (n - 1);
        }
        return totalWaysof1or2or3or4(n - 1) + totalWaysof1or2or3or4(n - 2) + totalWaysof1or2or3or4(n - 3)
                + totalWaysof1or2or3or4(n - 4);
    }

}
