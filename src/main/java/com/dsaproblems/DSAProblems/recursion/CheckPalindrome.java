package com.dsaproblems.DSAProblems.recursion;

public class CheckPalindrome {

    public static void main(String[] args) {

        String A = "abcba";
        System.out.println(isPalindromev1(A));
        System.out.println(isPalindromev2(A, 0, A.length() - 1));
    }

    private static boolean isPalindromev2(String A, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (A.charAt(start) == A.charAt(end)) {
            return isPalindromev2(A, start + 1, end - 1);
        }
        return false;
    }

    private static int isPalindromev1(String A) {
        if (A.length() == 1 || A.isEmpty()) {
            return 1;
        }
        if (A.charAt(0) == A.charAt(A.length() - 1)) {
            return isPalindromev1(A.substring(1, A.length() - 1));
        }
        return 0;
    }
}
