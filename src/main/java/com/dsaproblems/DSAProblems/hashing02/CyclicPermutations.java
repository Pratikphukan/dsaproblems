package com.dsaproblems.DSAProblems.hashing02;

public class CyclicPermutations {

    public static void main(String[] args) {
        String A = "1001";
        String B = "0011";
        System.out.println(areCyclicPermutationsv1(A, B));
        System.out.println(areCyclicPermutationsv2(A, B));
    }

    //left cyclic shift, firt is added to last
    private static int areCyclicPermutationsv2(String A, String B) {
        int n = B.length();
        int count = 0;
        for (int k = 0; k < n; k++) {
            if ((B.substring(k) + B.substring(0, k)).equals(A)) {
                count++;
            }
        }
        return count;
    }

    //right cyclic shift, last is added to first
    private static int areCyclicPermutationsv1(String A, String B) {
        int n = B.length();
        int count = 0;
        for (int k = 0; k < n; k++) {
            if ((B.substring(n - k) + B.substring(0, n - k)).equals(A)) {
                count++;
            }
        }
        return count;
    }
}
