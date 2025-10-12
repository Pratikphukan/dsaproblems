package com.dsaproblems.DSAProblems.dp01;

public class RegularExpressionII {
    public static void main(String[] args) {
//        String A = "aab";
//        String B = "c*a*b";

//        String A = "efwihfioghih35i";
//        String B = ".*";

        String A = "abbbc";
        String B = "ab*c";
        System.out.println(checkRegexMatchv1(A, B));
        System.out.println(checkRegexMatchv2(A, B));
    }

    private static int checkRegexMatchv2(String A, String B) {
        int n = A.length(), m = B.length();
        Boolean[][] dp = new Boolean[n + 1][m + 1];
        //return checkRegexMatchv21(A, B, n, m, dp) ? 1 : 0;
        return checkRegexMatchv22(A, B, n, m, dp) ? 1 : 0;
    }

    //working code
    private static boolean checkRegexMatchv22(String A, String B, int lenA, int lenB, Boolean[][] dp) {
        if (dp[lenA][lenB] != null) return dp[lenA][lenB];

        if (lenB == 0) {
            dp[lenA][lenB] = (lenA == 0);
            return dp[lenA][lenB];
        }

        boolean firstMatch = (lenA > 0) && (A.charAt(lenA - 1) == B.charAt(lenB - 1) || B.charAt(lenB - 1) == '.');

        if (lenB >= 2 && B.charAt(lenB - 1) == '*') {
            boolean isStarMatch = (lenA > 0) && (B.charAt(lenB - 2) == A.charAt(lenA - 1) || B.charAt(lenB - 2) == '.');
            dp[lenA][lenB] = (isStarMatch && checkRegexMatchv22(A, B, lenA - 1, lenB, dp)) ||
                    checkRegexMatchv22(A, B, lenA, lenB - 2, dp);
        } else {
            dp[lenA][lenB] = firstMatch && checkRegexMatchv22(A, B, lenA - 1, lenB - 1, dp);
        }
        return dp[lenA][lenB];
    }

    //working code
    private static boolean checkRegexMatchv21(String A, String B, int lenA, int lenB, Boolean[][] dp) {
        if (lenA == 0) {
            if (lenB == 0)
                dp[0][0] = true;
            else {
                if (B.charAt(lenB - 1) == '*') {
                    dp[0][lenB] = checkRegexMatchv21(A, B, 0, lenB - 2, dp);
                } else
                    dp[0][lenB] = false;
            }
        } else if (lenB == 0) {
            dp[lenA][0] = false;
        }
        if (dp[lenA][lenB] == null) {
            if (A.charAt(lenA - 1) == B.charAt(lenB - 1) || B.charAt(lenB - 1) == '.') {
                dp[lenA][lenB] = checkRegexMatchv21(A, B, lenA - 1, lenB - 1, dp);
            } else if (B.charAt(lenB - 1) == '*') {
                if (B.charAt(lenB - 2) == A.charAt(lenA - 1) || B.charAt(lenB - 2) == '.') {
                    dp[lenA][lenB] = checkRegexMatchv21(A, B, lenA - 1, lenB, dp) ||
                            checkRegexMatchv21(A, B, lenA, lenB - 2, dp);
                } else {
                    dp[lenA][lenB] = checkRegexMatchv21(A, B, lenA, lenB - 2, dp);
                }
            } else {
                dp[lenA][lenB] = false;
            }
        }
        return dp[lenA][lenB];
    }

    private static int checkRegexMatchv1(String A, String B) {
        return checkRegexMatchv1(A, B, A.length(), B.length()) ? 1 : 0;
    }

    //working code but throws TLE
    private static boolean checkRegexMatchv1(String A, String B, int lenA, int lenB) {
        if (lenA == 0) {
            if (lenB == 0) {
                return true;
            }
            if (B.charAt(lenB - 1) == '*') {
                return checkRegexMatchv1(A, B, 0, lenB - 2);
            }
            return false;
        }
        if (lenB == 0) {
            return false;
        }
        if (A.charAt(lenA - 1) == B.charAt(lenB - 1) || B.charAt(lenB - 1) == '.') {
            return checkRegexMatchv1(A, B, lenA - 1, lenB - 1);
        } else if (B.charAt(lenB - 1) == '*') {
            if (B.charAt(lenB - 2) == A.charAt(lenA - 1) || B.charAt(lenB - 2) == '.') {
                return checkRegexMatchv1(A, B, lenA - 1, lenB) || checkRegexMatchv1(A, B, lenA, lenB - 2);
            } else {
                return checkRegexMatchv1(A, B, lenA, lenB - 2);
            }
        }
        return false;
    }
}
