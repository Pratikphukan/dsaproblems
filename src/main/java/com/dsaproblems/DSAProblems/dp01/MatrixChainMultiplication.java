package com.dsaproblems.DSAProblems.dp01;

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        int[] test1 = {40, 20, 30, 10, 30};
        System.out.println(solvev1(test1));
    }

    private static int solvev1(int[] A) {
        // If the array A length is less than 2, multiplication is not possible;
        // hence return 0 as there is zero multiplication needed.
        if (A == null || A.length < 2) {
            return 0;
        }

        // n represents the number of matrices in the chain.
        // There are A.length - 1 matrices.
        int n = A.length - 1;

        // dp[i][j] will store the minimum multiplication cost for multiplying matrices from i to j.
        // We initialize a 2D dp array of size (n+1) x (n+1) to account for the indices starting at 1.
        // In our code, matrices are considered from 1 to n (both inclusive).
        int[][] dp = new int[n + 1][n + 1];

        // For a single matrix i, the cost of multiplication is 0.
        // dp[i][i] = 0 for all i from 1 to n.
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }

        // Chain length variable that determines the number of matrices in subproblem.
        // Start from chainLength 2 because chainLength 1 are already solved.
        // chainLength represents the number of matrices multiplied together.
        for (int chainLength = 2; chainLength <= n; chainLength++) {
            // i is the starting matrix index for the subproblem.
            for (int i = 1; i <= n - chainLength + 1; i++) {
                // j is the ending matrix index of the subproblem.
                int j = i + chainLength - 1;
                // Set dp[i][j] to a high value initially to help with minimization.
                dp[i][j] = Integer.MAX_VALUE;

                // Try every possible split between matrices i and j.
                for (int k = i; k <= j - 1; k++) {
                    // cost = cost of multiplying matrices from i to k
                    //      + cost of multiplying matrices from k+1 to j
                    //      + cost of multiplying the resultant two matrices.
                    // Here, A[i-1] x A[k] x A[j] gives the cost for the final multiplication.
                    int cost = dp[i][k] + dp[k + 1][j] + A[i - 1] * A[k] * A[j];

                    // If the computed cost is less than the current known cost, update dp[i][j].
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        // dp[1][n] represents the minimum multiplication cost for the entire chain.
        return dp[1][n];
    }
}
