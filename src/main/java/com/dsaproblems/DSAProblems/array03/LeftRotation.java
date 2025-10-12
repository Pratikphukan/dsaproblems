package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.List;

public class LeftRotation {

    public static void main(String[] args) {
        ArrayList<Integer> n = new ArrayList<>(List.of(1, 2, 3, 9, 17, 21));
        ArrayList<Integer> arr1 = new ArrayList<>(List.of(-3, 4, 2, 8, 7, 9, 6, 2, 10));
        System.out.println(leftRotateArrayv2(arr1, n));

        List<Integer> arr2 = List.of(-3, 4, 2, 8, 7, 9, 6, 2, 10);
        ArrayList<Integer> arr3 = new ArrayList<>(arr2);
        System.out.println(leftRotateArrayv1(arr3, n));
    }

    //instead of modifying reversedArray in place, create a copy of the array for each rotation to ensure the original array is used for all queries.
    public static ArrayList<ArrayList<Integer>> leftRotateArrayv2(List<Integer> input, List<Integer> n) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> reversedArray = new ArrayList<>(input);
        int len = input.size();
        int rotationFactor = 0;
        reverseSubarray(reversedArray, 0, len - 1);
        ArrayList<Integer> tempArray = null;
        for (int num : n) {
            tempArray = new ArrayList<>(reversedArray);
            rotationFactor = num % len;
            reverseSubarray(tempArray, 0, len - rotationFactor - 1);
            reverseSubarray(tempArray, len - rotationFactor, len - 1);
            ans.add(tempArray);
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> leftRotateArrayv1(List<Integer> input, List<Integer> n) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> reversedArray = null;
        int rotationFactor = 0;
        int len = input.size();
        for (int num : n) {
            reversedArray = new ArrayList<>(input);
            rotationFactor = num % len;
            reverseSubarray(reversedArray, 0, len - 1);
            reverseSubarray(reversedArray, 0, len - rotationFactor - 1);
            reverseSubarray(reversedArray, len - rotationFactor, len - 1);
            ans.add(reversedArray);
        }
        return ans;
    }

    public static void reverseSubarray(List<Integer> arr, int i, int j) {
        while (i < j) {
            swapv2(arr, i, j);
            i++;
            j--;
        }
    }

    //If the sum of arr.get(i) and arr.get(j) exceeds the maximum value of an integer, it will cause an overflow
    public static void swapv1(List<Integer> arr, int i, int j) {
        arr.set(i, arr.get(i) + arr.get(j));
        arr.set(j, arr.get(i) - arr.get(j));
        arr.set(i, arr.get(i) - arr.get(j));
    }

    public static void swapv2(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

}
