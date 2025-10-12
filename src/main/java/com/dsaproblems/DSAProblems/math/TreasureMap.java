package com.dsaproblems.DSAProblems.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreasureMap {

    public static void main(String[] args) {

        int[][] matrix1 = {{1, 2}};
        System.out.println(getLuckyNumbers(matrix1));

        int[][] matrix2 = {
                {1, 2},
                {3, 4}
        };
        System.out.println(getLuckyNumbers(matrix2));

        int[][] matrix3 = {
                {10, 99, 50},
                {20, 30, 40},
                {5, 60, 15}
        };
        System.out.println(getLuckyNumbers(matrix3));

        int[][] matrix4 = {
                {7, 8},
                {9, 6}
        };
        System.out.println(getLuckyNumbers(matrix4));
    }

    private static List<Integer> getLuckyNumbers(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[] colMax = new int[m]; //store the maximum value for each column
        for (int j = 0; j < m; j++) {
            colMax[j] = A[0][j]; //we can initialize with A[0][j] for each column
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Update the maximum value for column j if current element is higher
                if (A[i][j] > colMax[j]) {
                    colMax[j] = A[i][j];
                }
            }
        }

        List<Integer> luckyNumbers = getLuckyNumbers(A, m, colMax);

        // Sort the luckyNumbers list in ascending order as required by the problem
        Collections.sort(luckyNumbers);

        // Return the sorted list of lucky numbers
        return luckyNumbers;
    }

    private static List<Integer> getLuckyNumbers(int[][] A, int m, int[] colMax) {
        List<Integer> luckyNumbers = new ArrayList<>();

        // Loop through each row to check for lucky numbers
        for (int[] nums : A) {
            // Initialize minVal to maximum possible value (or first element of the row)
            int minVal = nums[0];
            // Save the column index of the minimum element
            int minIndex = 0;

            // Loop through columns in the row to find the smallest element
            for (int j = 0; j < m; j++) {
                // If current element is smaller than current minimum, update both minVal and column index
                if (nums[j] < minVal) {
                    minVal = nums[j];
                    minIndex = j;
                }
            }

            // After finding the minimum in the row, check if it's also the maximum in its column
            if (minVal == colMax[minIndex]) {
                // If it qualifies as a lucky number, add it to the list
                luckyNumbers.add(minVal);
            }
        }
        return luckyNumbers;
    }
}
