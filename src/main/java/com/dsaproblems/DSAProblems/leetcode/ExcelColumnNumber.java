package com.dsaproblems.DSAProblems.leetcode;

public class ExcelColumnNumber {

    public static void main(String[] args) {
        String str = "CV";
        System.out.println(titleToNumberv1(str));
        int num = 762;
        System.out.println(numberToTitlev1(num));
    }

    private static String numberToTitlev1(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            num--; // adjust to 0-indexed
            int remainder = num % 26;
            char letter = (char) (remainder + 'A');
            sb.append(letter);
            num /= 26;
        }
        return sb.reverse().toString();
    }

    private static int titleToNumberv1(String str) {
        int sum = 0, len = str.length(), mul = 1;
        for (int i = len - 1; i >= 0; i--) {
            int val = str.charAt(i) - 'A' + 1;
            sum += val * mul;
            mul *= 26;
        }
        return sum;
    }
}
