package com.dsaproblems.DSAProblems.strings;

public class PeriodOfAString {

    public static void main(String[] args) {
        String A = "abababab";
        System.out.println(getPeriodOfStringv1(A));
    }

    private static int getPeriodOfStringv1(String A) {
        int[] lps = createLps(A);
        return A.length() - lps[A.length() - 1];
    }

    private static int[] createLps(String A) {
        int n = A.length();
        int[] lps = new int[n];
        for (int i = 1; i < n; i++) {
            int x = lps[i - 1];
            while (A.charAt(i) != A.charAt(x)) {
                if (x == 0) {
                    x = -1;
                    break;
                }
                x = lps[x - 1];
            }
            lps[i] = x + 1;
        }
        return lps;
    }
}
