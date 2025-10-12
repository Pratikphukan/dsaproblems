package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class RotationGame {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(-3, 4, 5, -6, 8, 9, 10, -10, 8));
        int rotationFactor = 4;
        System.out.println(rotateArray(input, rotationFactor));


        input = new ArrayList<>(Arrays.asList(-3, 4, 5, -6, 8, 9, 10, -10, 8));
        BiFunction<List<Integer>, Integer, List<Integer>> rotateArrayFunction = (arr, k) -> {
            int len = arr.size();
            k = k % len;
            reverseSubArrayv2(arr, 0, len - 1);
            reverseSubArrayv2(arr, 0, k - 1);
            reverseSubArrayv2(arr, k, len - 1);
            return arr;
        };
        System.out.println(rotateArrayFunction.apply(input, rotationFactor));


        input = new ArrayList<>(Arrays.asList(-3, 4, 5, -6, 8, 9, 10, -10, 8));
        BiConsumer<List<Integer>, Integer> rotateArrayConsumer = (arr, k) -> {
            int len = arr.size();
            k = k % len;
            reverseSubArrayv2(arr, 0, len - 1);
            reverseSubArrayv2(arr, 0, k - 1);
            reverseSubArrayv2(arr, k, len - 1);
            for (Integer num : arr) {
                System.out.print(num + " ");
            }
        };
        rotateArrayConsumer.accept(input, rotationFactor);
    }

    private static List<Integer> rotateArray(List<Integer> input, int rotationFactor) {
        int len = input.size();
        rotationFactor = (rotationFactor > 0) ? rotationFactor % len : rotationFactor;
        reverseSubArrayv2(input, 0, len - 1);
        reverseSubArrayv2(input, 0, rotationFactor - 1);
        reverseSubArrayv2(input, rotationFactor, len - 1);
        return input;
    }

    private static void reverseSubArrayv1(List<Integer> input, int start, int end) {
        while (start < end) {
            input.set(start, input.get(start) + input.get(end));
            input.set(end, input.get(start) - input.get(end));
            input.set(start, input.get(start) - input.get(end));
            start++;
            end--;
        }
    }

    private static void reverseSubArrayv2(List<Integer> input, int start, int end) {
        while (start < end) {
            int temp = input.get(start);
            input.set(start, input.get(end));
            input.set(end, temp);
            start++;
            end--;
        }
    }

}
