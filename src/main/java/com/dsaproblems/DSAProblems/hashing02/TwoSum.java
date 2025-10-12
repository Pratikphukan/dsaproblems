package com.dsaproblems.DSAProblems.hashing02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(List.of(2, 4, 3, 4, 5, 3, 7, 8, 9, 5, 3));
        int target = 8;
        System.out.println(findAllPairsv1(input, target));
        System.out.println(findAllPairsv2(input, target));
    }

    private static List<List<Integer>> findAllPairsv2(List<Integer> nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int num : nums) {
            int complement = target - num;
            if (map.getOrDefault(num, 0) > 0 && map.getOrDefault(complement, 0) > 0) {
                if (num == complement && map.get(num) < 2) continue;
                ans.add(List.of(num, complement));
                map.put(num, map.get(num) - 1);
                map.put(complement, map.get(complement) - 1);
            }
        }
        return ans;
    }

    private static List<List<Integer>> findAllPairsv1(List<Integer> nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Integer num : nums) {
            int complement = target - num;
            if (map.containsKey(num) && map.containsKey(complement)) {
                if (num == complement && map.get(num) > 1) {
                    map.put(num, map.get(num) - 2);
                    if (map.get(num) == 0) {
                        map.remove(num);
                    }
                    ans.add(new ArrayList<>(List.of(num, num)));
                } else {
                    map.put(num, map.get(num) - 1);
                    if (map.get(num) == 0) {
                        map.remove(num);
                    }
                    map.put(complement, map.get(complement) - 1);
                    if (map.get(complement) == 0) {
                        map.remove(complement);
                    }
                    ans.add(new ArrayList<>(List.of(num, complement)));
                }
            }
        }
        return ans;
    }
}
