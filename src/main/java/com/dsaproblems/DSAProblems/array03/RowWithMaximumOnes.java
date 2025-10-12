package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;

public class RowWithMaximumOnes {

    public static void main(String[] args) {
        int[][] input = {
                {0, 0, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 0, 0, 0}
        }; //binary sorted matrix
        System.out.println(rowWithMaximumOnesv1(input));
        System.out.println(rowWithMaximumOnesv2(input));
    }

    //working code
    //time complexity is O(n + m) because each row is visited once, and each column is visited at most once.
    private static int rowWithMaximumOnesv2(int[][] A) {
        int n = A.length;
        int ans = 0, i, j;
        for (i = 0, j = n - 1; i < n && j >= 0; i++) {
            while (j >= 0 && A[i][j] == 1) {
                ans = i;
                j--;
            }
        }
        return ans;
    }

    //working code
    private static int rowWithMaximumOnesv1(int[][] input) {
        int maxRowIndex = -1;
        int maxCount = 0;
        for (int i = 0; i < input.length; i++) {
            int count = 0;
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == 1) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxRowIndex = i;
            }
        }
        return maxRowIndex;
    }
}
