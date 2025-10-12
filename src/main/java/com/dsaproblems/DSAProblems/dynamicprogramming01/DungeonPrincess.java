package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DungeonPrincess {

    public static void main(String[] args) {
        List<List<Integer>> A = new ArrayList<>();
//		A.add(new ArrayList<>(Arrays.asList(1, -1, 0)));
//		A.add(new ArrayList<>(Arrays.asList(-1, 1, -1)));
//		A.add(new ArrayList<>(Arrays.asList(1, 0, -1)));
        // -------//
        A.add(new ArrayList<>(Arrays.asList(-2, -3, 3)));
        A.add(new ArrayList<>(Arrays.asList(-5, -10, 1)));
        A.add(new ArrayList<>(Arrays.asList(10, 30, -5)));
        System.out.println(minHealthReqdv1(A, A.size() - 1, A.get(0).size() - 1));
    }

    //working code
    private static int minHealthReqdv1(List<List<Integer>> input, int i, int j) {
        if (i < 0 || j < 0) {
            return 1;
        }
        if (i == 0 || j == 0) {
            if (j > 0) {
                return Math.max(1, minHealthReqdv1(input, i, j - 1) - input.get(i).get(j));
            }
            return Math.max(1, minHealthReqdv1(input, i - 1, j) - input.get(i).get(j));
        }
        return Math.max(1, Math.min(
                minHealthReqdv1(input, i - 1, j),
                minHealthReqdv1(input, i, j - 1)) -
                input.get(i).get(j));
    }

}
