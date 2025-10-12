package com.dsaproblems.DSAProblems.hashing01;

import com.dsaproblems.DSAProblems.trie01.A;

import java.util.*;

public class SortArrayInGivenOrder {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(15, 5, 10, 6, 14));
        List<Integer> B = new ArrayList<>(Arrays.asList(8, 16, 6, 2, 13, 1, 12, 3, 14));
        System.out.println(sortAByBv1(A, B));
        System.out.println(sortAByBv2(A, B));
        System.out.println(sortAByBv3(A, B));
    }

    private static List<Integer> sortAByBv2(List<Integer> A, List<Integer> B) {
        Map<Integer, Integer> mp = new TreeMap<>();
        // stores the frequency of the elements of A
        for (int x : A) {
            mp.merge(x, 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int y : B) {
            // checks if y exists in A
            if (mp.get(y) != null) {
                while (mp.get(y) > 0) {
                    ans.add(y);
                    mp.put(y, mp.get(y) - 1);
                }
            }
        }
        Set<Integer> keys = mp.keySet();
        // append the elements that are not present in B
        for (Integer key : keys) {
            int t = mp.get(key);
            while (t > 0) {
                ans.add(key);
                t--;
            }
        }
        return ans;
    }

    //elements of B are unique
    private static List<Integer> sortAByBv3(List<Integer> A, List<Integer> B) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> freq = new TreeMap<>();
        for (int num : A) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : B) {
            int count = freq.getOrDefault(num, 0);
            for (int i = 0; i < count; i++) {
                ans.add(num);
            }
            freq.remove(num);
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }

    private static List<Integer> sortAByBv1(List<Integer> A, List<Integer> B) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        for (Integer num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer num : B) {
            if (map.containsKey(num)) { // check in the first array
                for (int j = 0; j < map.get(num); j++) {
                    ans.add(num);
                }
            }
            map.remove(num);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }

}
