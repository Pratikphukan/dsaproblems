package com.dsaproblems.DSAProblems.advancedJava.revision;

public class WaysToReachNthStair123 {

    public static void main(String[] args) {
        int n = 6;
        System.out.println(totalWaysv1(n));
        System.out.println(totalWaysv2(n));
        System.out.println(totalWaysv3(n));
    }

    private static int totalWaysv3(int n) {
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    private static int totalWaysv2(int n) {
        int[] dp = new int[n + 1];
        return totalWaysv2(n, dp);
    }

    private static int totalWaysv2(int n, int[] dp) {
        if (dp[n] == 0) {
            if (n == 0)
                dp[0] = 1;
            else if (n < 3)
                dp[n] = n;
            else
                dp[n] = totalWaysv2(n - 1, dp) +
                        totalWaysv2(n - 2, dp) +
                        totalWaysv2(n - 3, dp);
        }
        return dp[n];
    }

    // 3 ways->1 or 2 or 3 steps
    private static int totalWaysv1(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 3) {
            return n;
        }
        return totalWaysv1(n - 1) + totalWaysv1(n - 2) + totalWaysv1(n - 3);
    }
}
