package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindLongestConsecutiveSequence {

    public static void main(String[] args) {
        //-1, 8, 2, 3, 7, 1, 4, 9
        //1, 2, 3, 4, 5, 6, 7
        //7,6,5,4,3,2,1
        List<Integer> A = new ArrayList<>(Arrays.asList(7, 6, 5, 4, 3, 2, 1));
        System.out.println(findLongestConsecutive(A));
        System.out.println(findLongestConsecutive1(A));
        System.out.println(findLongestConsecutive2(A));
        System.out.println(findLongestConsecutive3(A));
        System.out.println(findLongestConsecutive4(A));
        System.out.println(findLongestConsecutivev1(A));
        System.out.println(findLongestConsecutivev2(A));
        System.out.println(findLongestConsecutivev3(A));
    }

    private static int findLongestConsecutivev3(List<Integer> input) {
        Map<Integer, Integer> mp = new HashMap<>();
        int maxCount = 0;
        for (int ele : input) {
            if (!mp.containsKey(ele)) {
                int lCount = 0;
                int rCount = 0;
                // lCount stores longest consecutive element till the current element - 1
                if (mp.containsKey(ele - 1)) {
                    lCount = mp.get(ele - 1);
                }
                // rCount stores longest consecutive element from the current element + 1
                if (mp.containsKey(ele + 1)) {
                    rCount = mp.get(ele + 1);
                }
                mp.put(ele, lCount + 1 + rCount);
                if (mp.containsKey(ele - lCount))
                    mp.put(ele - lCount, mp.get(ele));
                else
                    mp.put(ele - lCount, mp.get(ele));
                if (mp.containsKey(ele + rCount))
                    mp.put(ele + rCount, mp.get(ele));
                else
                    mp.put(ele + rCount, mp.get(ele));
                if (maxCount < lCount + 1 + rCount)
                    maxCount = lCount + 1 + rCount;
            }
        }
        return maxCount;
    }

    //working code
    //TC: O(N) average, O(N) worst case (each sequence is counted only once)
    //every element is visited at most twice (once as a start, once in the inner while loop)
    //SC: O(N) (for the HashSet)
    //throws TLE in leetcode
    private static int findLongestConsecutivev2(List<Integer> A) {
        Set<Integer> set = new HashSet<>(A);
        int ans = 1;
        for (int item : A) {
            int count = 1;
            int nextItem = item + 1;
            if (!set.contains(nextItem)) { //check if it is the end of sequence
                int prevItem = item - 1;
                while (set.contains(prevItem)) {
                    count++;
                    prevItem--;
                }
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    // optimised solution
    //TC: O(N) average, O(N) worst case (each sequence is counted only once)
    //every element is visited at most twice (once as a start, once in the inner while loop)
    //SC: O(N) (for the HashSet)
    //throws TLE in leetcode
    private static int findLongestConsecutivev1(List<Integer> A) {
        Set<Integer> set = new HashSet<>(A);
        int ans = 1;
        for (int item : A) {
            int count = 1;
            int prevItem = item - 1; // to avoid counting the same sequence multiple times
            if (!set.contains(prevItem)) {
                int nextItem = item + 1;
                while (set.contains(nextItem)) {
                    count++;
                    nextItem++;
                }
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    // optimised solution
    private static int findLongestConsecutive2(List<Integer> A) {
        int ans = 1;
        Set<Integer> set = new HashSet<>(A);
        for (int item : A) {
            if (!set.contains(item + 1)) { // to get the end of the sequence, it should not have a next element
                int count = 1;
                while (set.contains(--item)) {
                    count++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    //works in leetcode
    private static int findLongestConsecutive4(List<Integer> A) {
        if (A.isEmpty()) return 0;
        int longest = 1;
        Set<Integer> set = new HashSet<>(A);
        for (int num : set) {
            if (!set.contains(num + 1)) {
                int curr = num;
                int count = 1;
                while (set.contains(--curr)) count++;
                longest = Math.max(count, longest);
            }
        }
        return longest;
    }

    // optimised solution
    private static int findLongestConsecutive1(List<Integer> A) {
        int ans = 1;
        Set<Integer> set = new HashSet<>(A);
        for (int item : A) {
            if (!set.contains(item - 1)) { // to get the start of the sequence, it should not have a previous element
                int count = 1;
                while (set.contains(++item)) {
                    count++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    //works in leetcode
    private static int findLongestConsecutive3(List<Integer> A) {
        if (A.isEmpty()) return 0;
        int longest = 1;
        Set<Integer> set = new HashSet<>(A);
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curr = num;
                int count = 1;
                while (set.contains(++curr)) count++;
                longest = Math.max(count, longest);
            }
        }
        return longest;
    }

    // working but not optimised solution, throws TLE
    private static int findLongestConsecutive(List<Integer> A) {
        int ans = 1, count = 0;
        Set<Integer> set = new HashSet<>(A);
        for (Integer item : A) {
            count = 1;
            while (set.contains(++item)) {
                count++;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

}
