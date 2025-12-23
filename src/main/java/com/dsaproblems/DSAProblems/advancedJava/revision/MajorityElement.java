package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;

public class MajorityElement {

    public static void main(String[] args) {
        //2,2,1,1,1,2,2
        //2, 1, 2
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 2, 1, 2, 1, 2));
        System.out.println(findMajorityElementv1(input));
        System.out.println(findMajorityElementv2(input));
        System.out.println(findMajorityElementv3(input));
        System.out.println(findMajorityElementv4(input));

        //2,1,1,3,1,4,5,6
        //3, 3, 4, 3, 5
        int[] nums = {2, 1, 1, 3, 1, 4, 5, 6};
        System.out.println(findMajorityElementIIv1(nums));
        System.out.println(findMajorityElementIIv2(nums));

    }

    //working code
    // if you check cnt1 == 0 / cnt2 == 0 before testing if num equals an
    // existing candidate, so equal values can be (incorrectly) treated as new candidates.
    private static List<Integer> findMajorityElementIIv2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        // Phase 1: find candidates
        Integer cand1 = null, cand2 = null;
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (cand1 != null && num == cand1) {
                cnt1++;
            } else if (cand2 != null && num == cand2) {
                cnt2++;
            } else if (cnt1 == 0) {
                cand1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                cand2 = num;
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (cand1 != null && num == cand1) cnt1++;
            else if (cand2 != null && num == cand2) cnt2++;
        }
        int threshold = nums.length / 3;
        if (cand1 != null && cnt1 > threshold) result.add(cand1);
        if (cand2 != null && !Objects.equals(cand2, cand1) && cnt2 > threshold) result.add(cand2);

        return result;
    }

    private static List<Integer> findMajorityElementIIv1(int[] nums) {
        int minOccurrences = nums.length / 3;
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > minOccurrences) result.add(entry.getKey());
        }
        return result;
    }

    //working
    private static int findMajorityElementv3(List<Integer> input) {
        int minOccurrences = input.size() / 2;
        int ans = input.get(0);
        Map<Integer, Integer> elementToFrequency = new HashMap<>();
        for (int item : input) {
            int currFrequency = elementToFrequency.getOrDefault(item, 0) + 1;
            if (currFrequency > minOccurrences) {
                ans = item;
            }
            elementToFrequency.put(item, currFrequency);
        }
        return ans;
    }

    //The method implements the Boyer-Moore Voting Algorithm in two phases:
    // (1) scan to pick a candidate that might be the majority by tracking a running count;
    // (2) verify that the candidate actually appears more than n/2 times.
    // Time O(n), extra space O(1)
    private static int findMajorityElementv4(List<Integer> input) {
        int n = input.size();
        // Phase 1: find candidate
        Integer candidate = null;
        int count = 0;
        for (int num : input) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        // Phase 2: verify candidate
        if (candidate == null) return -1;
        count = 0;
        for (int num : input) {
            if (num == candidate) count++;
        }
        return (count > n / 2) ? candidate : -1;
    }

    //working
    private static int findMajorityElementv2(List<Integer> input) {
        int maj = input.get(0);
        int n = input.size();
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (input.get(i) == maj) {
                count++;
            } else if (count == 1) {
                maj = input.get(i);
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : input) {
            if (num == maj)
                count++;
        }
        if (count > n / 2)
            return maj;
        return -1;
    }

    //working code, Time and space complexity remain O(n)
    private static Integer findMajorityElementv1(List<Integer> input) {
        int minOccurrences = input.size() / 2;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : input) {
            int count = freq.getOrDefault(num, 0) + 1;
            if (count > minOccurrences) return num;
            freq.put(num, count);
        }
        return -1;
    }

}
