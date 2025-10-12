package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClosestMinMax {

    public static void main(String[] args) {
        //814,761,697,483,981
        List<Integer> input = new ArrayList<>(List.of(814, 761, 697, 483, 981));
        System.out.println(getSmallestSubarrayWithMinMaxv1(input));
        System.out.println(getSmallestSubarrayWithMinMaxv2(input));
        System.out.println(getSmallestSubarrayWithMinMaxv3(input));
    }

    private static int getSmallestSubarrayWithMinMaxv3(List<Integer> input) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : input) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        List<Integer> minIndices = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals(min)) {
                minIndices.add(i);
            }
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals(max)) {
                for (int minIndex : minIndices) {
                    minLen = Math.min(minLen, Math.abs(minIndex - i) + 1);
                }
            }
        }
        return minLen;
    }

    //working code
    private static int getSmallestSubarrayWithMinMaxv2(List<Integer> input) {
        Integer max = input.stream().max(Integer::compareTo).orElse(null);
        Integer min = input.stream().min(Integer::compareTo).orElse(null);

        if (Objects.equals(max, min)) return 1; // Edge case: all elements are same

        int lastMinIndex = -1, lastMaxIndex = -1;
        int minSubarrayLen = input.size();

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals(min)) {
                lastMinIndex = i;
                if (lastMaxIndex != -1) {
                    minSubarrayLen = Math.min(minSubarrayLen, lastMinIndex - lastMaxIndex + 1);
                }
            }
            if (input.get(i).equals(max)) {
                lastMaxIndex = i;
                if (lastMinIndex != -1) {
                    minSubarrayLen = Math.min(minSubarrayLen, lastMaxIndex - lastMinIndex + 1);
                }
            }
        }
        return minSubarrayLen;
    }

    //working code
    //optimize it to (O(N)) by tracking the last seen indices of min and max as you iterate
    //The optimized approach tracks the last seen indices of the minimum and maximum values
    // as you iterate through the list. Whenever you encounter either the min or max, you
    // check if the other has been seen before and update the smallest subarray length accordingly.
    private static int getSmallestSubarrayWithMinMaxv1(List<Integer> input) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int len = input.size();
        for (int num : input) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int lastMin = -1, lastMax = -1, minLen = len;
        for (int i = 0; i < len; i++) {
            if (input.get(i).equals(min)) {
                lastMin = i;
                if (lastMax != -1) {
                    minLen = Math.min(minLen, i - lastMax + 1);
                }
            }
            if (input.get(i).equals(max)) {
                lastMax = i;
                if (lastMin != -1) {
                    minLen = Math.min(minLen, i - lastMin + 1);
                }
            }
        }
        return minLen;
    }
}
