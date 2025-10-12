package com.dsaproblems.DSAProblems.subarrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SumOfSubarraySums {

    public static void main(String[] args) {
        //inputs:
        //-1, 4, 7, 6, -2, 7, 8, 10
        //2, 1, 3
        //1, 2, 3
        List<Integer> arr = new ArrayList<>(List.of(-1, 4, 7, 6, -2, 7, 8, 10));
        System.out.println(subarraySumv1(arr));
        System.out.println(subarraySumv2(arr));
        System.out.println(subarraySumv3(arr));
    }

    private static long subarraySumv3(List<Integer> input) {
        int len = input.size();
        return IntStream.range(0, len).mapToLong(idx -> ((long) input.get(idx) * (idx + 1) * (len - idx))).sum();
    }

    private static Long subarraySumv1(List<Integer> arr) {
        long sum = 0L;
        int len = arr.size();
        int end = (len - 1) / 2;
        for (int i = 0; i <= end; i++) {
            long mul = (long) (i + 1) * (long) (len - i);
            if (i == len - 1 - i) {
                sum += (long) arr.get(i) * mul;
            } else {
                sum += (long) (arr.get(i) + arr.get(len - 1 - i)) * mul;
            }
        }
        return sum;
    }

    private static Long subarraySumv2(List<Integer> arr) {
        long sum = 0L;
        int len = arr.size();
        for (int i = 0; i < len; i++) {
            sum += (long) arr.get(i) * (i + 1) * (len - i);
        }
        return sum;
    }

}
