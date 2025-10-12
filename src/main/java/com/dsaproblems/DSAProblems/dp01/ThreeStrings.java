package com.dsaproblems.DSAProblems.dp01;

public class ThreeStrings {

    public static void main(String[] args) {
        String A = "abab";
        String B = "aaab";
        String C = "aazb";
        System.out.println(lengthOfLCSv3(A, B, C));
    }

    private static int lengthOfLCSv3(String A, String B, String C) {
        int n = A.length(), m = B.length(), p = C.length();
        if (n == 0 || m == 0 || p == 0) {
            return 0;
        }
        int[][][] arr = new int[n + 1][m + 1][p + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= p; k++) {
                    if (A.charAt(i - 1) == B.charAt(j - 1) && B.charAt(j - 1) == C.charAt(k - 1)) {
                        arr[i][j][k] = 1 + arr[i - 1][j - 1][k - 1];
                    } else {
                        arr[i][j][k] = Math.max(Math.max(arr[i - 1][j][k], arr[i][j - 1][k]), arr[i][j][k - 1]);
                    }
                }
            }
        }
        return arr[n][m][p];
    }
}
