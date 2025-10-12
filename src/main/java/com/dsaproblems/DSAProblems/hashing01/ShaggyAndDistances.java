package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShaggyAndDistances {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(-1, 8, 2, 3, 7, 1, 4, 9, -1));
        // -1, 8, 2, 3, 7, 1, 4, 9
        // -1, 8, 2, 3, 7, 1, 4, 9,-1
        System.out.println(findSpecialPairWithLessLengthv1(input));
        System.out.println(findSpecialPairWithLessLengthv2(input));
    }

    private static int findSpecialPairWithLessLengthv2(List<Integer> input) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < input.size(); i++) {
            int key = input.get(i);
            if (!map.containsKey(key)) {
                map.put(key, i);
            } else {
                ans = Math.min(ans, i - map.get(key));
                map.put(key, i);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    private static int findSpecialPairWithLessLengthv1(List<Integer> input) {
        if (input.size() == 1) {
            return -1;
        }
        int minLength = input.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) { // don't use minLength as the original length, as it is getting updated
            int key = input.get(i);
            if (map.containsKey(key)) {
                minLength = Math.min(minLength, i - map.get(key));
            }
            map.put(key, i);
        }
        return (minLength == input.size()) ? -1 : minLength;
    }

}
