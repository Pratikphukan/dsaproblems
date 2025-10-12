package com.dsaproblems.DSAProblems.dp02;

import java.util.ArrayList;
import java.util.Arrays;

public class NoOfUniquePaths {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        System.out.println(A);
        System.out.println(noOfUniquePathsv1(A, A.size() - 1, A.get(0).size() - 1));
    }

    private static int noOfUniquePathsv1(ArrayList<ArrayList<Integer>> grid, int x, int y) {
        if (x == 0 || y == 0) {
            return 1;
        }
        return noOfUniquePathsv1(grid, x - 1, y) + noOfUniquePathsv1(grid, x, y - 1);
    }
}
