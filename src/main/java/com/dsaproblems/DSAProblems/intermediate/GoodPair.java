package com.dsaproblems.DSAProblems.intermediate;

import java.util.*;
import java.util.function.BiFunction;

public class GoodPair {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int B = 7;

        //working code
        BiFunction<List<Integer>, Integer, Integer> checkGoodPair1 = (arr, k) -> {
            Collections.sort(arr);
            int l = 0, r = arr.size() - 1;
            while (l < r) {
                if (arr.get(l) + arr.get(r) == k) {
                    return 1; // Found a good pair
                } else if (arr.get(l) + arr.get(r) < k) {
                    l++;
                } else {
                    r--;
                }
            }
            return 0; // No good pair found
        };
        System.out.println(checkGoodPair1.apply(input, B));

        //working code
        BiFunction<List<Integer>, Integer, Integer> checkGoodPair2 = (arr, k) -> {
            Set<Integer> set = new HashSet<>();
            for (int num : arr) {
                if (set.contains(k - num)) {
                    return 1; // Found a good pair
                }
                set.add(num);
            }
            return 0; // No good pair found
        };
        System.out.println(checkGoodPair2.apply(input, B));
    }


}
