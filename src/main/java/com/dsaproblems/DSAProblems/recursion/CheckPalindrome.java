package com.dsaproblems.DSAProblems.recursion;

public class CheckPalindrome {

    public static void main(String[] args) {

        //A man, a plan, a canal: Panama
        //abcba
        String A = "A man, a plan, a canal: Panama";

        System.out.println(isPalindromev1(A));

        System.out.println(isPalindromeAfterRemovingNonAlphav1(A));
        System.out.println(isPalindromeAfterRemovingNonAlphav2(A));

        System.out.println(isPalindromev2(A, 0, A.length() - 1));

    }

    private static boolean isPalindromeAfterRemovingNonAlphav2(String s) {
        int st = 0, ed = s.length() - 1;
        while (st < ed) {
            int l = s.charAt(st), r = s.charAt(ed);
            if (!Character.isLetterOrDigit(l)) {
                st++;
                continue;
            }
            if (!Character.isLetterOrDigit(r)) {
                ed--;
                continue;
            }
            if (Character.toLowerCase(l) != Character.toLowerCase(r)) return false;
            st++;
            ed--;
        }
        return true;
    }

    private static boolean checkNonAlphanumericChar(int ch) {
        return !(ch >= 'a' && ch <= 'z') &&
                !(ch >= 'A' && ch <= 'Z') &&
                !(ch >= '0' && ch <= '9');
    }


    public static boolean isPalindromeAfterRemovingNonAlphav1(String s) {
        if (s == null) return false;
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) sb.append(Character.toLowerCase(c));
        }
        return isPalindromev2(sb.toString(), 0, sb.length() - 1);
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
