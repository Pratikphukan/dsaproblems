package com.dsaproblems.DSAProblems.dynamicprogramming01;

public class EditDistance {

    public static void main(String[] args) {
        String A = "ac";
        String B = "abc";
//		String A = "abdxyl";
//		String B = "abgxl";
//		String A = "aaa";
//		String B = "aa";
        //System.out.println(noOfStepsToChangeAtoB(A, B, A.length() - 1, B.length() - 1));
        System.out.println(noOfStepsToChangeAtoBv1(A, B));
        System.out.println(noOfStepsToChangeAtoBv2(A, B, A.length(), B.length()));
    }

    //working code but throws TLE
    private static int noOfStepsToChangeAtoBv2(String A, String B, int lenA, int lenB) {
        if (lenA == 0 && lenB == 0) {
            return 0;
        }
        if (lenA == 0) {
            return 1 + noOfStepsToChangeAtoBv2(A, B, 0, lenB - 1); //all characters in A have been processed but characters in B remain, so we insert remaining characters in B into A
        }
        if (lenB == 0) {
            return 1 + noOfStepsToChangeAtoBv2(A, B, lenA - 1, 0);//all characters in A have been convereted into B, so we delete remaining characters in A
        }
        if (A.charAt(lenA - 1) == B.charAt(lenB - 1)) {
            return noOfStepsToChangeAtoBv2(A, B, lenA - 1, lenB - 1); //If the last characters match, move to the next pair
        }
        int deletion = 1 + noOfStepsToChangeAtoBv2(A, B, lenA - 1, lenB); //delete char from A
        int replacement = 1 + noOfStepsToChangeAtoBv2(A, B, lenA - 1, lenB - 1); //replace the char
        int insertion = 1 + noOfStepsToChangeAtoBv2(A, B, lenA, lenB - 1); //insert char into A
        return Math.min(Math.min(deletion, replacement), insertion);
    }

    private static int noOfStepsToChangeAtoB(String a, String b, int aLastIdx, int bLastIdx) {
        if (aLastIdx == -1 && bLastIdx == -1) {
            return 0;
        } else {
            if (bLastIdx == 0) {
                return 1 + noOfStepsToChangeAtoB(a, b, aLastIdx - 1, bLastIdx);
            }
            if (aLastIdx == 0) {
                return 1 + noOfStepsToChangeAtoB(a, b, aLastIdx, bLastIdx - 1);
            }
        }
        if (a.charAt(aLastIdx) == b.charAt(bLastIdx)) {
            return noOfStepsToChangeAtoB(a, b, aLastIdx - 1, bLastIdx - 1);
        }
        int deletion = 1 + noOfStepsToChangeAtoB(a, b, aLastIdx - 1, bLastIdx);
        int replacement = 1 + noOfStepsToChangeAtoB(a, b, aLastIdx - 1, bLastIdx - 1);
        int insertion = 1 + noOfStepsToChangeAtoB(a, b, aLastIdx, bLastIdx - 1);
        return Math.min(Math.min(deletion, replacement), insertion);
    }

    private static Integer noOfStepsToChangeAtoBv1(String A, String B) {
        int n = A.length(), m = B.length();
        Integer[][] arr = new Integer[n + 1][m + 1];
        return noOfStepsToChangeAtoBImpl(A, B, n, m, arr);
    }

    // working code
    private static Integer noOfStepsToChangeAtoBImpl(String a, String b, int n, int m, Integer[][] arr) {
        if (n == 0 && m == 0) {
            return 0;
        }
        if (m == 0) {
            return 1 + noOfStepsToChangeAtoBImpl(a, b, n - 1, m, arr);
        }
        if (n == 0) {
            return 1 + noOfStepsToChangeAtoBImpl(a, b, n, m - 1, arr);
        }
        if (arr[n][m] == null) {
            if (a.charAt(n - 1) == b.charAt(m - 1)) {
                arr[n][m] = noOfStepsToChangeAtoBImpl(a, b, n - 1, m - 1, arr);
            } else {
                int deletion = 1 + noOfStepsToChangeAtoBImpl(a, b, n - 1, m, arr);
                int replacement = 1 + noOfStepsToChangeAtoBImpl(a, b, n - 1, m - 1, arr);
                int insertion = 1 + noOfStepsToChangeAtoBImpl(a, b, n, m - 1, arr);
                arr[n][m] = Math.min(Math.min(deletion, replacement), insertion);
            }
        }
        return arr[n][m];
    }

}
