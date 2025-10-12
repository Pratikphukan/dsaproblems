package com.dsaproblems.DSAProblems.dynamicprogramming01;

public class RegularExpressionMatch {

    public static void main(String[] args) {
        // abxclm|a*lm
        // abc|*?
        // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa|a**************************************************************************************
//		String A = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//		String B = "a**************************************************************************************";

        String A = "abxclm";
        String B = "a*lm";

//        String A = "a";
//        String B = "?";

//        String A = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String B = "*";
        System.out.println(checkRegexMatchv1(A, B));
        System.out.println(checkRegexMatchv2(A, B));
        System.out.println(checkRegexMatchv3(A, B, A.length(), B.length()));
        System.out.println(checkRegexMatchv4(A, B));
        System.out.println(checkRegexMatchv5(A, B));
    }

    // working code
    private static int checkRegexMatchv1(String A, String B) {
        return checkRegexMatchImplv1(A, B, A.length() - 1, B.length() - 1) ? 1 : 0;
    }

    private static boolean checkRegexMatchImplv1(String A, String B, int alidx, int blidx) {
        if (alidx == -1) {
            if (blidx == -1)
                return true;
            else {
                if (B.charAt(blidx) == '*')
                    return checkRegexMatchImplv1(A, B, alidx, blidx - 1);
                return false;
            }
        }
        if (blidx == -1) {
            return false;
        }
        if (A.charAt(alidx) == B.charAt(blidx) || B.charAt(blidx) == '?') {
            return checkRegexMatchImplv1(A, B, alidx - 1, blidx - 1);
        } else if (B.charAt(blidx) == '*') {
            return checkRegexMatchImplv1(A, B, alidx - 1, blidx) || checkRegexMatchImplv1(A, B, alidx, blidx - 1);
        } else {
            return false;
        }
    }

    //working code
    private static boolean checkRegexMatchv3(String A, String B, int lenOfA, int lenOfB) {
        if (lenOfA == 0) {
            if (lenOfB == 0) {
                return true;
            }
            if (B.charAt(lenOfB - 1) == '*') {
                return checkRegexMatchv3(A, B, 0, lenOfB - 1);
            }
            return false;
        }
        if (lenOfB == 0) {
            return false;
        }
        if (A.charAt(lenOfA - 1) == B.charAt(lenOfB - 1) || B.charAt(lenOfB - 1) == '?') {
            return checkRegexMatchv3(A, B, lenOfA - 1, lenOfB - 1);
        } else if (B.charAt(lenOfB - 1) == '*') {
            return checkRegexMatchv3(A, B, lenOfA - 1, lenOfB) || checkRegexMatchv3(A, B, lenOfA, lenOfB - 1);
        } else {
            return false;
        }
    }


    private static int checkRegexMatchv2(String A, String B) {
        int n = A.length(), m = B.length();
        Boolean[][] dp = new Boolean[n + 1][m + 1];
        return checkRegexMatchImplv2(A, B, n, m, dp) ? 1 : 0;
    }

    private static boolean checkRegexMatchImplv2(String A, String B, int alidx, int blidx, Boolean[][] dp) {
        if (alidx == 0) {
            if (blidx == 0)
                return true;
            else {
                if (B.charAt(blidx - 1) == '*') {
                    dp[alidx][blidx] = checkRegexMatchImplv2(A, B, alidx, blidx - 1, dp);
                } else
                    return false;
            }
        }
        if (blidx == 0) {
            return false;
        }
        if (dp[alidx][blidx] == null) {
            if (A.charAt(alidx - 1) == B.charAt(blidx - 1) || B.charAt(blidx - 1) == '?') {
                dp[alidx][blidx] = checkRegexMatchImplv2(A, B, alidx - 1, blidx - 1, dp);
            } else if (B.charAt(blidx - 1) == '*') {
                dp[alidx][blidx] = checkRegexMatchImplv2(A, B, alidx - 1, blidx, dp)
                        || checkRegexMatchImplv2(A, B, alidx, blidx - 1, dp);
            } else {
                dp[alidx][blidx] = false;
            }
        }
        return dp[alidx][blidx];
    }

    //working code
    private static int checkRegexMatchv4(String A, String B) {
        int lenOfA = A.length();
        int lenOfB = B.length();
        Boolean[][] dp = new Boolean[lenOfA + 1][lenOfB + 1];
        return checkRegexMatchImplv4(A, B, lenOfA, lenOfB, dp) ? 1 : 0;
    }

    private static boolean checkRegexMatchImplv4(String A, String B, int lenOfA, int lenOfB, Boolean[][] dp) {
        if (lenOfA == 0) {
            if (lenOfB == 0)
                dp[0][0] = true;
            else if (B.charAt(lenOfB - 1) == '*')
                dp[0][lenOfB] = checkRegexMatchImplv4(A, B, 0, lenOfB - 1, dp);
            else
                dp[0][lenOfB] = false;
        }
        if (lenOfB == 0 && lenOfA > 0)
            dp[lenOfA][0] = false;
        if (dp[lenOfA][lenOfB] == null) {
            if (A.charAt(lenOfA - 1) == B.charAt(lenOfB - 1) || B.charAt(lenOfB - 1) == '?') {
                dp[lenOfA][lenOfB] = checkRegexMatchImplv4(A, B, lenOfA - 1, lenOfB - 1, dp);
            } else if (B.charAt(lenOfB - 1) == '*') {
                dp[lenOfA][lenOfB] = checkRegexMatchImplv4(A, B, lenOfA - 1, lenOfB, dp) ||
                        checkRegexMatchImplv2(A, B, lenOfA, lenOfB - 1, dp);
            } else {
                dp[lenOfA][lenOfB] = false;
            }
        }
        return dp[lenOfA][lenOfB];
    }

    //complete working code
    private static boolean checkRegexMatchv5(String s, String p) {
        int m = s.length();
        int n = p.length();
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
        return dp[m][n];
    }

}
