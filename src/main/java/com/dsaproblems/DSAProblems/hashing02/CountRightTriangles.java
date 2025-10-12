package com.dsaproblems.DSAProblems.hashing02;

import java.util.*;

public class CountRightTriangles {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3));
        List<Integer> B = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1));
        System.out.println(countRightTrianglesv1(A, B));
    }

    private static int countRightTrianglesv1(List<Integer> A, List<Integer> B) {
        Map<Integer, Integer> xCount = new HashMap<>();
        Map<Integer, Integer> yCount = new HashMap<>();
        int n = A.size();
        for (int i = 0; i < n; i++) {
            xCount.put(A.get(i), xCount.getOrDefault(A.get(i), 0) + 1);
            yCount.put(B.get(i), yCount.getOrDefault(B.get(i), 0) + 1);
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            int xFreq = xCount.get(A.get(i)) - 1;
            int yFreq = yCount.get(B.get(i)) - 1;
            total += xFreq * yFreq;
        }
        return total;
    }
}
