package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByColor {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 0, 1, 2));
        // 0, 1, 2, 0, 1, 2
        /*
         * 2, 0, 0, 1, 0, 0, 2, 2, 1, 1, 0, 0, 1, 0, 2, 1, 1, 0, 1, 0, 1, 2, 2, 2, 0, 0,
         * 1, 0, 2, 1, 1, 2, 1, 2, 2, 1, 0, 2, 2, 1, 1, 1, 0, 1, 0, 1, 0, 2, 1, 2, 0, 2,
         * 0, 1, 1, 0, 2, 2, 1, 2, 0, 2, 1, 1, 1, 2, 0, 1, 0, 2, 2, 1, 0, 0, 1, 0, 1, 0,
         * 0, 0, 1, 2, 1, 1, 0, 2, 1, 2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 1, 1, 0, 2, 1, 2,
         * 2, 2, 1, 2, 2, 0, 1, 0, 1, 2, 1, 1, 0, 1, 2, 0, 1, 0, 2, 2, 1, 2, 1, 0, 2, 2,
         * 1, 1, 0, 2, 1, 2
         */
//		System.out.println(sortByColorv1(input));
//		System.out.println(sortByColorv2(input));
//		System.out.println(sortByColorv3(input));
        // System.out.println(sortByColorv4(input));
        System.out.println(sortByColorv5(input));
    }

    //working code
    private static List<Integer> sortByColorv5(List<Integer> input) {
        int left = 0, right = input.size() - 1, idx = 0;
        while (idx <= right) {
            int val = input.get(idx);
            if (val == 2) {
                int temp = input.get(right);
                input.set(right, 2);
                input.set(idx, temp);
                right--;
            } else if (val == 0) {
                int temp = input.get(left);
                input.set(left, 0);
                input.set(idx, temp);
                left++;
                idx++;
            } else {
                idx++;
            }
        }
        return input;
    }

    // not working code
    private static List<Integer> sortByColorv4(List<Integer> input) {
        int left = 0;
        int right = input.size() - 1;
        int idx = 0;
        int temp = 0;
        while (idx < right) {
            if (input.get(idx) == 2) {
                while (idx != right && input.get(right) == 2) {
                    right--;
                }
                temp = input.get(right);
                input.set(right, 2);
                input.set(idx, temp);
                right--;
                idx++;
            } else if (input.get(idx) == 0) {
                while (idx != left && input.get(left) == 0) {
                    left++;
                }
                temp = input.get(left);
                input.set(left, 0);
                input.set(idx, temp);
                left++;
                idx++;
            } else {
                idx++;
            }
        }
        return input;
    }

    public static List<Integer> sortByColorv2(List<Integer> input) {
        Collections.sort(input);
        return input;
    }

    private static List<Integer> sortByColorv1(List<Integer> input) {
        Map<Integer, Integer> colorTofrequency = new HashMap<>();
        for (Integer element : input) {
            colorTofrequency.put(element, colorTofrequency.getOrDefault(element, 0) + 1);
        }
        List<Integer> sortedColors = new ArrayList<>();
        Integer color = null;
        Integer frequency = 0;
        for (Map.Entry<Integer, Integer> entry : colorTofrequency.entrySet()) {
            color = entry.getKey();
            frequency = entry.getValue();
            if (color == 0) {
                for (int i = 0; i < frequency; i++) {
                    sortedColors.add(0, color);
                }
            } else if (color == 2) {
                for (int i = 0; i < frequency; i++) {
                    sortedColors.add(color);
                }
            }
        }
        int startIdx = colorTofrequency.get(0);
        if (colorTofrequency.containsKey(1)) {
            for (int i = 0; i < colorTofrequency.get(1); i++) {
                sortedColors.add(startIdx + i, 1);
            }
        }
        return sortedColors;
    }

    private static List<Integer> sortByColorv3(List<Integer> input) {
        Map<Integer, Integer> colorTofrequency = new HashMap<>();
        for (Integer element : input) {
            colorTofrequency.put(element, colorTofrequency.getOrDefault(element, 0) + 1);
        }
        List<Integer> sortedColors = new ArrayList<>();
        Integer color = null;
        Integer frequency = 0;
        if (colorTofrequency.containsKey(0)) {
            frequency = colorTofrequency.get(0);
            color = 0;
            for (int i = 0; i < frequency; i++) {
                sortedColors.add(0, color);
            }
        }
        if (colorTofrequency.containsKey(2)) {
            frequency = colorTofrequency.get(2);
            color = 2;
            for (int i = 0; i < frequency; i++) {
                sortedColors.add(color);
            }
        }
        int startIdx = colorTofrequency.get(0);
        if (colorTofrequency.containsKey(1)) {
            frequency = colorTofrequency.get(1);
            for (int i = 0; i < frequency; i++) {
                sortedColors.add(startIdx + i, 1);
            }
        }
        return sortedColors;
    }

}
