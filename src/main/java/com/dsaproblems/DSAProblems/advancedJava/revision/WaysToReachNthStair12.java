package com.dsaproblems.DSAProblems.advancedJava.revision;

public class WaysToReachNthStair12 {

    public static void main(String[] args) {
        int n = 15;
        System.out.println(totalWaysv1(n));
        System.out.println(totalWaysv2(n));
        System.out.println(totalWaysv3(n));
        System.out.println(totalWaysv4(n));
        System.out.println(totalWaysv5(n));
        System.out.println(totalWaysv6(n));
    }

    private static int totalWaysv6(int n) {
        if (n < 2) return n;
        int mod = 1000000007;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int c = (a % mod + b % mod) % mod;
            a = b;
            b = c;
        }
        return b;
    }

    private static int totalWaysv5(int n) {
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] % 1000000007 + dp[i - 2] % 1000000007) % 1000000007;
        }
        return dp[n];
    }

    private static int totalWaysv4(int n) {
        int[] dp = new int[n + 1];
        return totalWaysv4(n, dp);
    }

    //working code
    private static int totalWaysv4(int n, int[] dp) {
        if (dp[n] == 0) {
            if (n == 0)
                dp[0] = 1;
            else if (n < 3)
                dp[n] = n;
            else
                dp[n] = (totalWaysv4(n - 1, dp) % 1000000007 +
                        totalWaysv4(n - 2, dp) % 1000000007) % 1000000007;
        }
        return dp[n];
    }

    // 2 ways->1 or 2 steps
    // if there only two ways 1 and 2 steps: then the
    // series->1,1,2,3,5,....(fibonacci starting from 1)
    // if there are three ways 1, 2 and 3 stesp: then the
    // series->1,1,2,4,7,13,24,.....
    private static int totalWaysv3(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 3) {
            return n;
        }
        return totalWaysv3(n - 1) + totalWaysv3(n - 2);
    }

    //working, TLE, taking 0th stair is 1
    private static int totalWaysv2(int n) {
        if (n < 2) {
            return 1;
        }
        return totalWaysv2(n - 1) + totalWaysv2(n - 2);
    }

    //working, TLE, taking 0th stair is 0
    private static int totalWaysv1(int n) {
        if (n < 3) {
            return n;
        }
        return totalWaysv1(n - 1) + totalWaysv1(n - 2);
    }
}
