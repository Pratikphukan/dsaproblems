package com.dsaproblems.DSAProblems.array01;

public class MaxZerosOutOfAllRowsInMatrix {

    public static void main(String[] args) {
        int[][] A = {
                {0, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        System.out.println(maxNoOfZerosInAv1(A));
        //System.out.println(maxNoOfZerosInAv2(A));
    }

    //not working
    public static int maxNoOfZerosInAv2(int[][] A) {
        int m = A.length, n = A[0].length;
        int row = 0, col = n - 1; // start from top-right
        int maxZeros = 0;

        while (row < m && col >= 0) {
            if (A[row][col] == 0) {
                row++; // move down
                while (row < m && A[row][col] == 0) {
                    col++;
                }
                maxZeros = Math.max(maxZeros, col);
            } else {
                col--; // move left
            }
        }
        return maxZeros;
    }

    private static int maxNoOfZerosInAv1(int[][] A) {
        if (A == null || A.length == 0) return 0;

        int maxZeros = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null || A[i].length == 0) continue;
            int lastZeroIndex = getLastIndexOfZero(A[i]);
            int zerosCount = (lastZeroIndex == -1) ? 0 : lastZeroIndex + 1;
            if (zerosCount > maxZeros) maxZeros = zerosCount;
        }
        return maxZeros;
    }

    private static int getLastIndexOfZero(int[] row) {
        int l = 0;
        int h = row.length - 1;
        int ans = -1;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (row[mid] == 0) {
                ans = mid;      // found a zero, try to move right to find last zero
                l = mid + 1;
            } else {
                // row[mid] == 1 (since values are 0 or 1), move left
                h = mid - 1;
            }
        }
        return ans; // -1 if no zero, otherwise index of last zero
    }
}
