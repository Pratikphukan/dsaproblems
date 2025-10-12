package com.dsaproblems.DSAProblems.dp01;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String A = "abde";
        String B = "acbef";
        System.out.println(lengthOfLCSv1(A, B, A.length(), B.length()));
        System.out.println(lengthOfLCSv2(A, B));
        System.out.println(lengthOfLCSv3(A, B));
    }

    private static int lengthOfLCSv3(String A, String B) {
        int n = A.length(), m = B.length();
        if (n == 0 || m == 0) {
            return 0;
        }
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
        return arr[n][m];
    }

    private static int lengthOfLCSv2(String A, String B) {
        int n = A.length(), m = B.length();
        Integer[][] arr = new Integer[n + 1][m + 1];
        return lengthOfLCSv2(A, B, n, m, arr);
    }

    private static int lengthOfLCSv2(String A, String B, int lenA, int lenB, Integer[][] arr) {
        if (arr[lenA][lenB] == null) {
            if (lenA == 0 || lenB == 0) {
                arr[lenA][lenB] = 0;
            } else if (A.charAt(lenA - 1) == B.charAt(lenB - 1)) {
                arr[lenA][lenB] = 1 + lengthOfLCSv2(A, B, lenA - 1, lenB - 1, arr);
            } else {
                arr[lenA][lenB] = Math.max(lengthOfLCSv2(A, B, lenA - 1, lenB, arr), lengthOfLCSv2(A, B, lenA, lenB - 1, arr));
            }
        }
        return arr[lenA][lenB];
    }

    private static int lengthOfLCSv1(String A, String B, int lenA, int lenB) {
        if (lenA == 0 || lenB == 0) {
            return 0;
        }
        if (A.charAt(lenA - 1) == B.charAt(lenB - 1)) {
            return 1 + lengthOfLCSv1(A, B, lenA - 1, lenB - 1);
        }
        return Math.max(
                lengthOfLCSv1(A, B, lenA - 1, lenB),
                lengthOfLCSv1(A, B, lenA, lenB - 1));
    }
}
