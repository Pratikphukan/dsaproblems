package com.dsaproblems.DSAProblems.subarrays;

import java.util.*;

public class FindDistinctNumbersInWindow {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 4, 3));
        int B = 3;

        // 31,51,31,51,31,31,31,31,31,31,51,31,31->3
        // 1, 2, 1, 3, 4, 3->3
        System.out.println(findDistinctNumbersInWindow2(A, B));
        System.out.println(findDistinctNumbersInWindowv1(A, B));
        System.out.println(findDistinctNumbersInWindowv2(A, B));
        System.out.println(findDistinctNumbersInWindowv3(A, B));
    }

    //working code
    private static ArrayList<Integer> findDistinctNumbersInWindowv3(List<Integer> A, int B) {
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> count = new ArrayList<>();
        int len = A.size();
        for (int i = 0; i < B; i++) {
            map.put(A.get(i), map.getOrDefault(A.get(i), 0) + 1);
        }
        count.add(map.size());
        for (int i = B; i < len; i++) {
            int exclude = A.get(i - B);
            int include = A.get(i);
            map.put(exclude, map.get(exclude) - 1);
            if (map.get(exclude) == 0) {
                map.remove(exclude);
            }
            map.put(include, map.getOrDefault(include, 0) + 1);
            count.add(map.size());
        }
        return count;
    }

    //working code
    private static ArrayList<Integer> findDistinctNumbersInWindowv2(List<Integer> A, int B) {
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> count = new ArrayList<>();
        int len = A.size();
        for (int i = 0; i < B; i++) {
            map.put(A.get(i), map.getOrDefault(A.get(i), 0) + 1);
        }
        count.add(map.size());
        for (int i = 1; i <= len - B; i++) {
            int exclude = A.get(i - 1);
            int include = A.get(i + B - 1);
            map.put(exclude, map.get(exclude) - 1);
            if (map.get(exclude) == 0) {
                map.remove(exclude);
            }
            map.put(include, map.getOrDefault(include, 0) + 1);
            count.add(map.size());
        }
        return count;
    }

    // working solution
    private static List<Integer> findDistinctNumbersInWindowv1(List<Integer> A, int B) {
        ArrayList<Integer> countList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Integer item = null;
        Integer excludedElement = null;
        for (int i = 0; i < A.size(); i++) {
            item = A.get(i);
            map.put(item, map.getOrDefault(item, 0) + 1);
            if ((i - B + 1) >= 0) {
                countList.add(map.size());
                excludedElement = A.get(i - B + 1);
                map.put(excludedElement, map.get(excludedElement) - 1);
                if (map.get(excludedElement) == 0) {
                    map.remove(excludedElement);
                }
            }
        }
        return countList;
    }

    private static List<Integer> findDistinctNumbersInWindow2(List<Integer> A, int B) {
        List<Integer> countList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            int item = A.get(i);
            map.put(item, map.getOrDefault(item, 0) + 1);
            if ((i - B + 1) >= 0) {
                countList.add(map.size());
                int excludedElement = A.get(i - B + 1);
                map.put(excludedElement, map.get(excludedElement) - 1);
                if (map.get(excludedElement) == 0) {
                    map.remove(excludedElement);
                }
            }
        }
        return countList;
    }
}
