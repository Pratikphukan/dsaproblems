package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;
import java.util.stream.IntStream;

public class FindMissingPositiveNumber {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(-2, 1, 3, 2, 6, 3, 8));

        //System.out.println(findFirstMissingPositiveIntegerv1(input));
        System.out.println(findFirstMissingPositiveIntegerv2(input));
        System.out.println(findFirstMissingPositiveIntegerv3(input));
        // System.out.println(findFirstMissingPositiveInteger2(input));
        // System.out.println(findFirstMissingPositiveInteger2Extended(input));
    }


    //working solution
    private static int findFirstMissingPositiveIntegerv2(List<Integer> input) {
        Set<Integer> set = new HashSet<>(input);
        int n = input.size();
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return n + 1;
    }

    private static int findFirstMissingPositiveInteger2Extended(List<Integer> A) { // working code
        int len = A.size();
        boolean[] bool = new boolean[len];
        A.stream().filter(item -> item > 0 && item <= len).forEach(item -> bool[item - 1] = true);
        return IntStream.range(0, len).filter(j -> !bool[j]).findFirst().orElse(len) + 1;
    }

    //working solution
    private static int findFirstMissingPositiveIntegerv3(List<Integer> A) {
        int len = A.size();
        boolean[] bool = new boolean[len + 1];
        for (int item : A) {
            if (item > 0 && item <= len) {
                bool[item] = true;
            }
        }
        for (int j = 1; j <= len; j++) {
            if (!bool[j]) {
                return j;
            }
        }
        return len + 1;
    }

    private static int findFirstMissingPositiveIntegerv1(List<Integer> A) {
        int len = A.size();
        for (int i = 0; i < len; i++) {
            if (A.get(i) <= 0) {
                A.set(i, len + 2); // if all numbers from 1 to the length of the list is present, then answer will
                // be len+1, but otherwise it will be len+2
            }
        }
        for (int i = 0; i < len; i++) {
            int ele = Math.abs(A.get(i));
            if (ele <= len) {
                A.set(ele - 1, -1 * Math.abs(A.get(ele - 1)));
            }
        }
        int i = 0;
        for (; i < len; i++) {
            if (A.get(i) > 0) {
                return i + 1;
            }
        }
        return i + 1;
    }

}
