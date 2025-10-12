package com.dsaproblems.DSAProblems.dp01;

public class RegularExpressionMatch {

    public static void main(String[] args) {
        String A = "abxclm";
        String B = "a*lm";
        System.out.println(checkRegexMatchv1(A, B, A.length(), B.length()));
        System.out.println(checkRegexMatchv2(A, B));
        System.out.println(checkRegexMatchv3(A, B));
        System.out.println(checkRegexMatchv4(A, B));
    }

    //working code
    private static int checkRegexMatchv4(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[] prev = new boolean[n + 1];
        boolean[] curr = new boolean[n + 1];
        prev[0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*')
                prev[j] = prev[j - 1];
        }

        for (int i = 1; i <= m; i++) {
            curr[0] = false;
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*')
                    curr[j] = curr[j - 1] || prev[j];
                else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1))
                    curr[j] = prev[j - 1];
                else
                    curr[j] = false;
            }
            boolean[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n] ? 1 : 0;
    }

    //working code, O(m*n) time, O(m*n) space
    private static int checkRegexMatchv3(String s, String p) {
        int m = s.length();
        int n = p.length();
        //dp array is a 2D boolean table where dp[i][j] stores whether the first i characters of the string s match the first j characters of the pattern p (with * and ? wildcards)
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // Handle patterns starting with '*'
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1]; // '*' can match an empty sequence
            }
        }

        // Fill the DP table
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    // '*' can match zero characters or one or more characters
                    // dp[i-1][j]: '*' matches at least one character in s
                    // dp[i][j-1]: '*' matches zero characters in s
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    // Characters match or '?' is used, move to the next characters
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // For other cases where characters don't match, dp[i][j] remains false.
            }
        }

        // The result is stored in the last cell of the DP table
        return dp[m][n] ? 1 : 0;
    }

    private static int checkRegexMatchv2(String A, String B) {
        int n = A.length(), m = B.length();
        Boolean[][] dp = new Boolean[n + 1][m + 1];
        return checkRegexMatchv2(A, B, n, m, dp) ? 1 : 0;
    }

    private static boolean checkRegexMatchv2(String A, String B, int lenA, int lenB, Boolean[][] dp) {
        if (lenA == 0) {
            if (lenB == 0)
                dp[0][0] = true;
            else {
                if (B.charAt(lenB - 1) == '*') {
                    dp[lenA][lenB] = checkRegexMatchv2(A, B, lenA, lenB - 1, dp);
                } else
                    dp[0][lenB] = false;
            }
        }
        if (lenB == 0) {
            dp[lenA][0] = false;
        }
        if (dp[lenA][lenB] == null) {
            if (A.charAt(lenA - 1) == B.charAt(lenB - 1) || B.charAt(lenB - 1) == '?') {
                dp[lenA][lenB] = checkRegexMatchv2(A, B, lenA - 1, lenB - 1, dp);
            } else if (B.charAt(lenB - 1) == '*') {
                dp[lenA][lenB] = checkRegexMatchv2(A, B, lenA - 1, lenB, dp) ||
                        checkRegexMatchv2(A, B, lenA, lenB - 1, dp);
            } else {
                dp[lenA][lenB] = false;
            }
        }
        return dp[lenA][lenB];
    }

    private static boolean checkRegexMatchv1(String A, String B, int lenA, int lenB) {
        if (lenA == 0) {
            if (lenB == 0) {
                return true;
            }
            if (B.charAt(lenB - 1) == '*') {
                return checkRegexMatchv1(A, B, 0, lenB - 1);
            }
            return false;
        }
        if (lenB == 0) {
            return false;
        }
        if (A.charAt(lenA - 1) == B.charAt(lenB - 1) || B.charAt(lenB - 1) == '?') {
            return checkRegexMatchv1(A, B, lenA - 1, lenB - 1);
        } else if (B.charAt(lenB - 1) == '*') {
            return checkRegexMatchv1(A, B, lenA - 1, lenB) || checkRegexMatchv1(A, B, lenA, lenB - 1);
        } else {
            return false;
        }
    }
}
