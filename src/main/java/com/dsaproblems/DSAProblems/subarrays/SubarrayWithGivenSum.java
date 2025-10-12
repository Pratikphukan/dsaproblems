package com.dsaproblems.DSAProblems.subarrays;

import com.dsaproblems.DSAProblems.ll.threads02.AvoidRacing;

import javax.lang.model.type.ArrayType;
import java.util.*;

public class SubarrayWithGivenSum {

    public static void main(String[] args) {
        // 5, 10, 20, 100, 105|110
        // 1, 2, 3, 4, 5|5
        //1, 2 | 2
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int targetSum = 5;
        System.out.println(findFirstSubarrayWithSumK(input, targetSum));
        System.out.println(findFirstSubarrayWithSumKv1(input, targetSum));
        System.out.println(findFirstSubarrayWithSumKv2(input, targetSum));
        System.out.println(findFirstSubarrayWithSumKv3(input, targetSum));
    }

    //same as findFirstSubarrayWithSumKv2
    private static ArrayList<Integer> findFirstSubarrayWithSumKv3(List<Integer> input, int targetSum) {
        int len = input.size();
        int left = 0, right = 0;
        long sum = 0;
        while (right < len) {
            sum += input.get(right);
            while (sum > targetSum && left <= right) {
                sum -= input.get(left);
                left++;
            }
            if (sum == targetSum) {
                // current window sum = B
                ArrayList<Integer> ans = new ArrayList<>();
                for (int i = left; i <= right; i++) ans.add(input.get(i));
                return ans;
            }
            right++;
        }
        return new ArrayList<>(List.of(-1));
    }

    //working code for non-negative integers (O(n) time, sliding window)
    private static ArrayList<Integer> findFirstSubarrayWithSumKv2(List<Integer> A, int targetSum) {
        int left = 0, sum = 0;
        for (int right = 0; right < A.size(); right++) {
            sum += A.get(right);
            while (sum > targetSum && left <= right) {
                sum -= A.get(left);
                left += 1;
            }
            if (sum == targetSum) {
                ArrayList<Integer> ans = new ArrayList<>();
                for (int i = left; i <= right; i++) ans.add(A.get(i));
                return ans;
            }
        }
        return new ArrayList<>(List.of(-1));
    }

    // working solution, faster approach for non negative integers
    private static ArrayList<Integer> findFirstSubarrayWithSumKv1(List<Integer> input, int targetSum) {
        int len = input.size();
        int left = 0, right = 0;
        long sum = input.get(left);
        ArrayList<Integer> ans = new ArrayList<>();
        while (right < len) {
            if (sum == targetSum) {
                // current window sum = B
                for (int i = left; i <= right; i++)
                    ans.add(input.get(i));
                return ans;
            } else if (sum < targetSum) {
                // current window sum < B
                right++;
                if (right < len)
                    sum += input.get(right);
            } else {
                // current window sum > B
                sum -= input.get(left);
                left++;
                if (left > right && left < len - 1) {
                    right++;
                    sum += input.get(right);
                }
            }
        }
        return new ArrayList<>(List.of(-1));
    }

    // sum(i->j)=pf(j)-pf(i-1)=>pf(j)-sum(i->j)= pf(i-1)
    // working solution for general arrays (with negatives), prefix sum + hashmap is needed
    private static List<Integer> findFirstSubarrayWithSumK(List<Integer> input, int targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        long sum = 0;
        int start = -1, end = -1;
        for (int i = 0; i < input.size(); i++) {
            sum += input.get(i); //maintain a running sum
            if (sum == targetSum) { //record the subarray from the 0 index
                start = 0;
                end = i;
                break;
            }
            if (map.containsKey(sum - targetSum)) { //a subarray with the target sum is found between the stored index + 1 and the current index
                start = map.get(sum - targetSum) + 1;
                end = i;
                break;
            }
            map.put(sum, i); //store prefix sums and their indices
        }
        List<Integer> ans = new ArrayList<>();
        if (start == -1) {
            ans.add(-1);
            return ans;
        }
        for (int i = start; i <= end; i++) {
            ans.add(input.get(i));
        }
        return ans;
    }

}
