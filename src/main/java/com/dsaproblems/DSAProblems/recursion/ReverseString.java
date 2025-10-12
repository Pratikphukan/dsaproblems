package com.dsaproblems.DSAProblems.recursion;

public class ReverseString {

    public static void main(String[] args) {
        String A = "scaleracademy";
        StringBuilder sb = new StringBuilder(A);
        reverseStringv1(sb, 0, sb.length() - 1);
        System.out.println(sb.toString());
    }

    private static void reverseStringv1(StringBuilder A, int start, int end) {
        if (start >= end) {
            return; // Base case: when the start index meets or exceeds the end index
        }
        char temp = A.charAt(start);
        A.setCharAt(start, A.charAt(end));
        A.setCharAt(end, temp);
        reverseStringv1(A, start + 1, end - 1);
    }
}
