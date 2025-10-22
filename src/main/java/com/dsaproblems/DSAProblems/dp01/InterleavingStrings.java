package com.dsaproblems.DSAProblems.dp01;

public class InterleavingStrings {

    public static void main(String[] args) {
        String A = "aabcc";
        String B = "dbbca";
        String C = "aadbbcbcac";
        System.out.println(isInterleavev1(A, B, C));
        System.out.println(isInterleavev2(A, B, C));
    }

    public static int isInterleavev2(String A, String B, String C) {
        int m = A.length(), n = B.length(), k = C.length();
        if (m + n != k) return 0;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] && B.charAt(j - 1) == C.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            dp[0] = dp[0] && A.charAt(i - 1) == C.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                dp[j] = (dp[j] && A.charAt(i - 1) == C.charAt(i + j - 1)) ||
                        (dp[j - 1] && B.charAt(j - 1) == C.charAt(i + j - 1));
            }
        }
        return dp[n] ? 1 : 0;
    }

    public static int isInterleavev1(String A, String B, String C) {
        // Get lengths of A, B, and C
        int m = A.length();
        int n = B.length();
        int k = C.length();

        // Early return: if combined lengths of A and B don't equal C's length, interleaving is impossible.
        if (m + n != k) {
            return 0;
        }

        // Initialize a 2D DP table with dimensions (m+1) x (n+1)
        // dp[i][j] is true if first i characters of A and first j characters of B can form first i+j characters of C.
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: empty A and empty B can form empty C
        dp[0][0] = true;

        // Fill in the first row (i = 0, only using B to match C)
        for (int j = 1; j <= n; j++) {
            // If previous state is true and current character of B matches C, then dp[0][j] is true.
            dp[0][j] = dp[0][j - 1] && B.charAt(j - 1) == C.charAt(j - 1);
        }

        // Fill in the first column (j = 0, only using A to match C)
        for (int i = 1; i <= m; i++) {
            // If previous state is true and current character of A matches C, then dp[i][0] is true.
            dp[i][0] = dp[i - 1][0] && A.charAt(i - 1) == C.charAt(i - 1);
        }

        // Populate the DP table for both A and B
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Check if the current character from A matches the corresponding character in C
                // and the state dp[i-1][j] is true.
                boolean fromA = dp[i - 1][j] && A.charAt(i - 1) == C.charAt(i + j - 1);

                // Check if the current character from B matches the corresponding character in C
                // and the state dp[i][j-1] is true.
                boolean fromB = dp[i][j - 1] && B.charAt(j - 1) == C.charAt(i + j - 1);

                // If either condition is true, then set dp[i][j] to true.
                dp[i][j] = fromA || fromB;
            }
        }

        // Return 1 if dp[m][n] is true (valid interleaving), else return 0.
        return dp[m][n] ? 1 : 0;
    }
}
