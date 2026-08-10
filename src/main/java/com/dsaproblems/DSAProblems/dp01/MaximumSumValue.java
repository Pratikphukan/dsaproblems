package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumSumValue {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 5, -3, 4, -2));
        int B = 2, C = 1, D = -1;
        //Find the maximum value of A[i]*B + A[j]*C + A[k]*D, where 1 <= i <= j <= k <= N
        System.out.println(findMaximumValuev1(A, B, C, D));
        System.out.println(findMaximumValuev2(A, B, C, D));
    }

    private static int findMaximumValuev1(List<Integer> A, int B, int C, int D) {
        int n = A.size();
        int[][] dp = new int[n + 1][3]; // dp array to store answer of previous states
        for (int i = 0; i <= n; i++) {
            dp[i][0] = dp[i][1] = dp[i][2] = -10000;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], A.get(i - 1) * B); // Maximum value of A[i]*B
            dp[i][1] = Math.max(dp[i - 1][1], dp[i][0] + A.get(i - 1) * C); // Maximum value of A[i]*B + A[j]*C
            dp[i][2] = Math.max(dp[i - 1][2], dp[i][1] + A.get(i - 1) * D); // Maximum value of A[i]*B + A[j]*C + A[k]*D
        }
        return dp[n][2];
    }

    //The fact that we update first, then immediately use it for second, and then second for third naturally handles i <= j <= k
    //Time:  O(N)
    //Space: O(1)
    private static int findMaximumValuev2(List<Integer> A, int B, int C, int D) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        for (int x : A) {
            first = Math.max(first, x * B);
            second = Math.max(second, first + x * C);
            third = Math.max(third, second + x * D);
        }
        return third;
    }
}
