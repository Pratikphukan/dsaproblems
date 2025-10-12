package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpeedVariation {

    public static void main(String[] args) {
//        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 4));
//        int cars = 3;
//        int targetSpeed = 1;
//        int threshold = 2;

//        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 5, 6));
//        int cars = 4;
//        int targetSpeed = 2;
//        int threshold = 2;

        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int cars = 4;
        int targetSpeed = 1;
        int threshold = 2;
        System.out.println(findSpeedVariationv1(cars, targetSpeed, threshold, input));
        System.out.println(findSpeedVariationv2(cars, targetSpeed, threshold, input));
    }

    private static String findSpeedVariationv2(int cars, int targetSpeed, int threshold, List<Integer> input) {
        Collections.sort(input);
        int count = 0, left = 0, right = 1;
        while (left < input.size() - 1) {
            while (right < input.size() && input.get(right) - input.get(left) < targetSpeed) {
                right++;
            }
            if (right < input.size()) {
                count += cars - right;
            }
            left++;
            if (right <= left) right = left + 1;
        }
        return count >= threshold ? "YES" : "NO";
    }

    //all elements are positive integers
    //not working
    private static String findSpeedVariationv1(int cars, int targetSpeed, int threshold, List<Integer> input) {
        Collections.sort(input);
        int count = 0, left = 0, right = 1;
        while (right < input.size() && left < right) {
            int diff = input.get(right) - input.get(left);
            if (diff >= targetSpeed) {
                //anything to the right of idx right will also satisfy the condition
                //count the values to the right of idx right
                int vals = cars - right; //[cars-1, right]-> right-cars-1+1->cars-right
                count += vals;
                left++;
            } else {
                right++;
            }
            if (left == right) right++;
        }
        return count >= threshold ? "YES" : "NO";
    }
}
