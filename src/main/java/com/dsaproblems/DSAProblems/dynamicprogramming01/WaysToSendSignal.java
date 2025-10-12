package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.List;

public class WaysToSendSignal {

    private static int mod = 1000000007;

    public static void main(String[] args) {
        int n = 49;
        System.out.println(noOfPossibilitiesv1(n));
        System.out.println(noOfPossibilitiesv2(n, 0) + noOfPossibilitiesv2(n, 1));
        System.out.println(noOfPossibilitiesv3(n));
    }

    //not working for input > 42
    private static Integer noOfPossibilitiesv3(int n) {
        Integer[][] dp = new Integer[2][n + 1];
        return noOfPossibilitiesv3(n, 0, dp) +
                noOfPossibilitiesv3(n, 1, dp);
    }


    private static Integer noOfPossibilitiesv3(int n, int endBit, Integer[][] dp) {
        if (dp[endBit][n] == null) {
            if (n == 1) {
                dp[endBit][1] = 1;
                return dp[endBit][1];
            }
            if (endBit == 1) {
                dp[1][n] = noOfPossibilitiesv3(n - 1, 0, dp) % mod;
            } else {
                dp[0][n] = (noOfPossibilitiesv3(n - 1, 0, dp) % mod +
                        noOfPossibilitiesv3(n - 1, 1, dp) % mod) % mod;
            }
        }
        return dp[endBit][n];
    }

    //not working for input > 42
    private static int noOfPossibilitiesv2(int n, int endBit) {
        if (n == 1) {
            return 1;
        }
        if (endBit == 1) {
            return noOfPossibilitiesv2(n - 1, 0) % mod;
        }
        return (noOfPossibilitiesv2(n - 1, 0) % mod +
                noOfPossibilitiesv2(n - 1, 1) % mod) % mod;
    }

    //working code
    private static int noOfPossibilitiesv1(int n) { // working code
        List<Integer> endInZeroPossibilities = new ArrayList<>();
        List<Integer> endInOnePossibilities = new ArrayList<>();
        endInOnePossibilities.add(1);
        endInZeroPossibilities.add(1);
        for (int i = 1; i < n; i++) {
            int prevZero = endInZeroPossibilities.get(i - 1) % 1000000007;
            int prevOne = endInOnePossibilities.get(i - 1) % 1000000007;
            endInZeroPossibilities.add((prevZero + prevOne) % 1000000007);
            endInOnePossibilities.add(prevZero);
        }
        return (endInOnePossibilities.get(n - 1) + endInZeroPossibilities.get(n - 1)) % 1000000007;
    }

}
