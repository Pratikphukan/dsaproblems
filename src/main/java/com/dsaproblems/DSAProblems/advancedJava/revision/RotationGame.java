package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RotationGame {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(-3, 4, 5, -6, 8, 9, 10, -10, 8));
        int rotationFactor = 4;
        System.out.println(rightRotateArrayv1(input, rotationFactor));
        System.out.println(leftRotateArrayv1(input, rotationFactor));
    }

    private static ArrayList<Integer> leftRotateArrayv1(List<Integer> input, int B) {
        int len = input.size();
        int rotationFactor = B > len ? B % len : B;
        ArrayList<Integer> rotatedArray = new ArrayList<>(input);
        reverseSubArray(rotatedArray, 0, len - 1);
        reverseSubArray(rotatedArray, len - rotationFactor, len - 1);
        reverseSubArray(rotatedArray, 0, len - rotationFactor - 1);
        return rotatedArray;
    }

    private static ArrayList<Integer> rightRotateArrayv1(List<Integer> input, int B) {
        int len = input.size();
        int rotationFactor = B > len ? B % len : B;
        ArrayList<Integer> rotatedArray = new ArrayList<>(input);
        reverseSubArray(rotatedArray, 0, len - 1);
        reverseSubArray(rotatedArray, 0, rotationFactor - 1);
        reverseSubArray(rotatedArray, rotationFactor, len - 1);
        return rotatedArray;
    }

    private static void reverseSubArray(ArrayList<Integer> input, int start, int end) {
        while (start < end) {
            int temp = input.get(start);
            input.set(start, input.get(end));
            input.set(end, temp);
            start++;
            end--;
        }
    }
}
