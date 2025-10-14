package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;

public class SpiralOrderMatrixII {

    public static void main(String[] args) {
        int A = 4;
        System.out.println(generateMatrixv1(A));
        System.out.println(generateMatrixv2(A));
    }

    private static ArrayList<ArrayList<Integer>> generateMatrixv2(int A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int r = 0; r < A; r++) {
            Integer[] row = new Integer[A];
            Arrays.fill(row, 0);
            ans.add(new ArrayList<>(Arrays.asList(row)));
        }
        int num = 1, top = 0, bottom = A - 1, left = 0, right = A - 1;
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) ans.get(top).set(j, num++);
            top++;
            for (int i = top; i <= bottom; i++) ans.get(i).set(right, num++);
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) ans.get(bottom).set(j, num++);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) ans.get(i).set(left, num++);
                left++;
            }
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> generateMatrixv1(int A) {
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
