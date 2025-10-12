package com.dsaproblems.DSAProblems.array01;

public class PermutationSorter {

    public static int findMinimumOperations(int[] arr) {
        int n = arr.length;

        // Case 0: Already sorted
        if (isSorted(arr)) return 0;

        // Case 1: Sorted if reversed
        int[] reversed = arr.clone();
        reverse(reversed);
        if (isSorted(reversed)) return 1;

        // Case 2: Sorted if rotated (moving first to last some times)
        if (canBeSortedByRotations(arr)) return 1;

        // Case 3: Sorted if one rotation + reverse
        for (int i = 0; i < n; i++) {
            arr = rotate(arr);
            int[] temp = arr.clone();
            reverse(temp);
            if (isSorted(temp)) return 2;
        }

        // Case 4: Otherwise, 3 moves are enough (rotate + reverse + rotate)
        return 3;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    private static void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    private static int[] rotate(int[] arr) {
        int n = arr.length;
        int[] rotated = new int[n];
        for (int i = 1; i < n; i++) {
            rotated[i - 1] = arr[i];
        }
        rotated[n - 1] = arr[0];
        return rotated;
    }

    private static boolean canBeSortedByRotations(int[] arr) {
        int n = arr.length;
        int[] temp = arr.clone();
        for (int i = 0; i < n; i++) {
            if (isSorted(temp)) return true;
            temp = rotate(temp);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{
                5, 4, 3, 2, 1
        };
        System.out.println(findMinimumOperations(arr));
    }
}
