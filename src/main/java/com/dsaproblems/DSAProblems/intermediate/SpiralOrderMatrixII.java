package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;

public class SpiralOrderMatrixII {

    public static void main(String[] args) {
        int A = 3;
        System.out.println(generateMatrix(A));
    }

    public static ArrayList<ArrayList<Integer>> generateMatrix(int A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int r = 0; r < A; r++) {
            Integer[] row = new Integer[A];
            Arrays.fill(row, 0);
            ans.add(new ArrayList<>(Arrays.asList(row)));
        }
        int i = 0, j = 0, k = 1;
        while (A > 1) {
            int l = 1;
            for (; l < A; l++) {
                ans.get(i).set(j, k);
                j++;
                k++;
            }
            for (l = 1; l < A; l++) {
                ans.get(i).set(j, k);
                i++;
                k++;
            }
            for (l = 1; l < A; l++) {
                ans.get(i).set(j, k);
                j--;
                k++;
            }
            for (l = 1; l < A; l++) {
                ans.get(i).set(j, k);
                i--;
                k++;
            }
            A -= 2;
            i++;
            j++;
        }
        if (A == 1) {
            ans.get(i).set(i, k);
        }
        return ans;
    }
}
