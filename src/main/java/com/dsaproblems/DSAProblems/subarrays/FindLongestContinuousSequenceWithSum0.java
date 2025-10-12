package com.dsaproblems.DSAProblems.subarrays;

import com.dsaproblems.DSAProblems.hashing01.ListLimits;

import java.util.*;

public class FindLongestContinuousSequenceWithSum0 {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(3, 3, 4, -5, -2, 2, 1, -3, 3, -1, 5, -4, -1));
        // 1, 2, -3
        // 2, 2, 1, -3, 4, 3, 1, -2, -3, 2
        // -9, -13, 6, -28, 27, -5, -27, 17, 15, -17, -25, 6, -8, 2, -13, -13, -23, 21,
        // -4, 22, -9, -10, 0, -28, -11, 8, 8, 17
        // 3, 3, 4, -5, -2, 2, 1, -3, 3, -1, 5, -4, -1
        System.out.println(findLongestSubarrayWithSum0A(A));
        System.out.println(findLongestSubarrayWithSum0AOptimized(A));
        System.out.println(findLongestSubarrayWithSum0B(A));
        System.out.println(findLongestSubarrayWithSum0v1(A));
    }

    private static ArrayList<Integer> findLongestSubarrayWithSum0v1(List<Integer> A) {
        List<Integer> prefix = new ArrayList<>();
        prefix.add(A.get(0));
        for (int i = 1; i < A.size(); i++) {
            prefix.add(prefix.get(i - 1) + A.get(i));
        }
        Map<Integer, ListLimits> map = new HashMap<>();
        map.put(0, new ListLimits(-1, null));
        for (int i = 0; i < prefix.size(); i++) {
            int key = prefix.get(i);
            if (map.containsKey(key)) {
                map.get(key).setEndIdx(i);
            } else {
                map.put(key, new ListLimits(i, null));
            }
        }
        int maxLength = 0;
        int startIdx = -1;
        int endIdx = -1;
        for (Map.Entry<Integer, ListLimits> entry : map.entrySet()) {
            if (entry.getValue().getEndIdx() != null) {
                if (maxLength < (entry.getValue().getEndIdx() - entry.getValue().getStartIdx())) {
                    maxLength = entry.getValue().getEndIdx() - entry.getValue().getStartIdx();
                    startIdx = entry.getValue().getStartIdx();
                    endIdx = entry.getValue().getEndIdx();
                }
                if (maxLength == (entry.getValue().getEndIdx() - entry.getValue().getStartIdx())) {
                    if (startIdx > entry.getValue().getStartIdx()) {
                        maxLength = entry.getValue().getEndIdx() - entry.getValue().getStartIdx();
                        startIdx = entry.getValue().getStartIdx();
                        endIdx = entry.getValue().getEndIdx();
                    }
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = startIdx + 1; i <= endIdx; i++) {
            ans.add(A.get(i));
        }
        return ans;
    }

    //working code
    private static List<Integer> findLongestSubarrayWithSum0B(List<Integer> A) {
        List<Long> prefix = new ArrayList<>();
        long sum = 0L;
        for (long item : A) {
            sum += item;
            prefix.add(sum);
        }
        int maxLen = 0, s = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < prefix.size(); i++) {
            long key = prefix.get(i);
            if (key == 0) {
                if (maxLen < (i + 1)) {
                    maxLen = i + 1;
                    s = 0;
                }
            } else {
                if (!map.containsKey(key)) {
                    map.put(key, i);
                } else {
                    int firstIdx = map.get(key);
                    if (maxLen < (i - firstIdx)) {
                        maxLen = i - firstIdx;
                        s = firstIdx + 1;
                    }
                }
            }
        }
        ArrayList<Integer> sol = new ArrayList<>();
        for (int i = s; i < s + maxLen; i++) {
            sol.add(A.get(i));
        }
        return sol;
    }

    private static List<Integer> findLongestSubarrayWithSum0A(List<Integer> A) {
        List<Integer> prefix = new ArrayList<>();
        int sum = 0;
        for (Integer item : A) {
            sum += item;
            prefix.add(sum);
        }
        int currentMaxNoOfElements = 0;
        int startIdx = 0;
        int endIdx = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < prefix.size(); i++) {
            int key = prefix.get(i);
            if (!map.containsKey(key)) {
                map.put(key, i);
            } else {
                int firstIdx = map.get(key);
                if (currentMaxNoOfElements < (i - firstIdx)) {
                    currentMaxNoOfElements = i - firstIdx;
                    startIdx = firstIdx;
                    endIdx = i;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = startIdx + 1; i <= endIdx; i++) {
            ans.add(A.get(i));
        }
        return ans;
    }

    // Optimized version that calculates running sum on the fly
    //working code, The method is already efficient with O(n) time and O(n) space
    private static List<Integer> findLongestSubarrayWithSum0AOptimized(List<Integer> A) {
        int maxLen = 0, startIdx = -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int runningSum = 0;
        for (int i = 0; i < A.size(); i++) {
            runningSum += A.get(i);

            if (!map.containsKey(runningSum)) {
                map.put(runningSum, i);
            } else {
                int firstIdx = map.get(runningSum);
                if (maxLen < (i - firstIdx)) {
                    maxLen = i - firstIdx;
                    startIdx = firstIdx + 1;
                }
            }
        }
        return maxLen > 0 ? new ArrayList<>(A.subList(startIdx, startIdx + maxLen)) :
                new ArrayList<>();
    }
}
