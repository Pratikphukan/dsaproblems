package com.dsaproblems.DSAProblems.hashing02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DiffkII {

    public static void main(String[] args) {
        //1, 5, 3; 2
        List<Integer> arr = new ArrayList<>(List.of(11, 85, 100, 44, 3, 32, 96, 72, 93, 76, 67, 93, 63, 5, 10, 45, 99, 35, 13));
        int k = 60;
        System.out.println(checkDiff(arr, k));
        System.out.println(checkDiff1(arr, k));
        System.out.println(checkDiff2(arr, k));
        System.out.println(checkDiff3(arr, k));
        System.out.println(checkDiff4(arr, k));
    }

    //working code
    private static int checkDiff4(List<Integer> A, int B) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : A) {
            // Check if num + B exists in the map
            if (hashMap.containsKey(num + B)) {
                return 1;
            }
            // Check if num - B exists in the map
            if (hashMap.containsKey(num - B)) {
                return 1;
            }
            // Add the current number to the map
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        return 0;
    }

    //ab + a' = b + a', working code
    private static int checkDiff3(List<Integer> A, int B) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : A) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        int n = 0;
        for (int num : A) {
            n = num + B;
            if (hashMap.containsKey(n)) {
                if (num != n || hashMap.get(n) > 1)
                    return 1;
            }
            n = num - B;
            if (hashMap.containsKey(n)) {
                if (num != n || hashMap.get(n) > 1)
                    return 1;
            }
        }
        return 0;
    }

    // wrong code. will not work
    private static int checkDiff2(List<Integer> A, int B) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.size(); i++) {
            if (set.contains(Math.abs(A.get(i) - B))) {
                return 1;
            } else {
                set.add(A.get(i));
            }
        }
        return 0;
    }

    // WRONG CODE, will not work
    private static int checkDiff1(List<Integer> A, int B) {
        Set<Integer> set = new HashSet<>(A);
        for (int i = 0; i < A.size(); i++) {
            if (set.contains(B + A.get(i))) {
                return 1;
            }
        }
        return 0;
    }

    // working solution, because there are two possiblities
    // one is target = A(i)+B, other is target = A(i)-B
    private static int checkDiff(List<Integer> arr, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.size(); i++) {
            if (set.contains(arr.get(i) + k)) {
                return 1;
            }
            if (set.contains(arr.get(i) - k)) {
                return 1;
            }
            set.add(arr.get(i));
        }
        return 0;
    }

}
