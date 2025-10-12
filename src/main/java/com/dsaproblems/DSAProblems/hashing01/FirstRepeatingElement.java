package com.dsaproblems.DSAProblems.hashing01;

import java.util.*;

public class FirstRepeatingElement {

    public static void main(String[] args) {
        //10, 5, 3, 4, 3, 5, 6
        //6, 10, 5, 4, 9, 120
        //8, 15, 1, 10, 5, 19, 19, 3, 5, 6, 6, 2, 8, 2, 12, 16, 3
        List<Integer> arr1 = new ArrayList<>(List.of(10, 5, 3, 4, 3, 5, 6));
        System.out.println(firstRepeatingElementv1(arr1));
        System.out.println(firstRepeatingElementv2(arr1));
        System.out.println(firstRepeatingElementv3(arr1));
    }

    //working code, Overall space complexity: O(n)
    private static int firstRepeatingElementv3(List<Integer> arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int minIdx = arr.size();
        for (int i = 0; i < arr.size(); i++) {
            if (map.containsKey(arr.get(i))) {
                minIdx = Math.min(minIdx, map.get(arr.get(i)));// Found a repeating element
            } else {
                map.put(arr.get(i), i);
            }
        }
        return minIdx < arr.size() ? arr.get(minIdx) : -1;
    }

    private static int firstRepeatingElementv2(List<Integer> arr) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (Integer num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    //working code, Overall space complexity: O(n)
    private static Integer firstRepeatingElementv1(List<Integer> arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer num : arr) {
            if (map.get(num) > 1) {
                return num;
            }
        }
        return -1;
    }
}
