package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstSubarrayWithGivenSum {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        // 12,1,50,39,32,23,22,44,17,5,9,12,10,50,26,5,23,38,31,5,34,8,21,11,24,44,18,19,6,31,3,47,5,2,33,44,14,9->58
        // 1, 2, 3, 4, 5->5
        // 1, 1, 2, 4, 5->9
        int sumToFind = 5;
        System.out.println(findFirstSubarrayWithGivenSum(input, sumToFind));
        System.out.println(findFirstSubarrayWithGivenSumv1(input, sumToFind));
        System.out.println(findFirstSubarrayWithGivenSumv2(input, sumToFind));
        System.out.println(findFirstSubarrayWithGivenSumv3(input, sumToFind));
        System.out.println(findFirstSubarrayWithGivenSumv4(input, sumToFind));
    }

    // not working code
    private static ArrayList<Integer> findFirstSubarrayWithGivenSumv3(List<Integer> A, int B) {
        int l = 0, h = 1;
        int sum = A.get(l) + A.get(h);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while (l < h && h < A.size()) {
            if (sum < B) {
                h++;
                if (h < A.size()) {
                    sum += A.get(h);
                }
            } else if (sum > B) {
                sum -= A.get(l);
                l++;
                if (l > h && h < A.size() - 1) {
                    h++;
                }
            } else {
                for (int i = l; i <= h; i++) {
                    ans.add(A.get(i));
                }
                break;
            }
        }
        if (ans.isEmpty()) {
            ans.add(-1);
            return ans;
        }
        return ans;
    }

    //working code
    private static List<Integer> findFirstSubarrayWithGivenSumv4(List<Integer> input, int sumToFind) {
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int sum = 0;
        for (int right = 0; right < input.size(); right++) {
            sum += input.get(right);
            while (sum > sumToFind && left <= right) {
                sum -= input.get(left);
                left++;
            }
            if (sum == sumToFind) {
                for (int i = left; i <= right; i++) {
                    ans.add(input.get(i));
                }
                return ans;
            }
        }
        ans.add(-1);
        return ans;
    }

    // working code
    private static List<Integer> findFirstSubarrayWithGivenSumv2(List<Integer> input, int sumToFind) {
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = 0;
        long sum = input.get(0);
        while (right < input.size()) {
            if (sum == sumToFind) {
                for (int i = left; i <= right; i++) {
                    ans.add(input.get(i));
                }
                return ans;
            } else if (sum < sumToFind) {
                right++;
                if (right < input.size()) {
                    sum += input.get(right);
                }
            } else {
                sum -= input.get(left);
                left++;
                if (left > right && left < input.size() - 1) {
                    right++;
                    sum += input.get(right);
                }
            }
        }
        ans.add(-1);
        return ans;
    }

    //working code
    private static List<Integer> findFirstSubarrayWithGivenSumv1(List<Integer> input, int sumToFind) {
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int sum = input.get(0);
        if (sum == sumToFind) {
            ans.add(sum);
            return ans;
        }
        for (int right = 1; right < input.size(); right++) {
            sum += input.get(right);
            while (sum > sumToFind) {
                sum -= input.get(left);
                left++;
            }
            if (sum == sumToFind) {
                for (int i = left; i <= right; i++) {
                    ans.add(input.get(i));
                }
                break;
            }
        }
        if (ans.isEmpty()) {
            ans.add(-1);
            return ans;
        }
        return ans;
    }

    private static List<Integer> findFirstSubarrayWithGivenSum(List<Integer> input, int sumToFind) {
        int left = 0;
        int right = 0;
        int sum = input.get(0);
        List<Integer> ans = new ArrayList<>();
        if (sum == sumToFind) {
            ans.add(sum);
            return ans;
        }
        while (right < input.size()) {
            if (sum > sumToFind) {
                sum -= input.get(left);
                left++;
                if (left > right && left < input.size() - 1) {
                    right++;
                    sum += input.get(right);
                }
            } else if (sum < sumToFind) {
                right++;
                if (right < input.size()) {
                    sum += input.get(right);
                }
            } else {
                ans = new ArrayList<>();
                for (int i = left; i <= right; i++) {
                    ans.add(input.get(i));
                }
                break;
            }
        }
        if (ans.isEmpty())
            ans.add(-1);
        return ans;
    }

}
