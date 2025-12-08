package com.dsaproblems.DSAProblems.leetcode;

public class VerifyPreorderSerializationBT {

    public static void main(String[] args) {
        String A = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(checkPreorderOfBTv1(A));
    }

    private static boolean checkPreorderOfBTv1(String A) {
        int slots = 1;
        String[] nodes = A.split(",");
        for (String node : nodes) {
            if (slots == 0) return false;
            slots--;
            if (!node.equals("#")) slots += 2;
        }
        return slots == 0;
    }
}
