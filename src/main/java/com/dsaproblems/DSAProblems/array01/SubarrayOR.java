package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubarrayOR {

    public static void main(String[] args) {
        //7, 8, 9, 10
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 4, 6, 8));
        System.out.println(findSubarrayORSumv1(input));
        System.out.println(findSubarrayORSumv2(input));
    }

    private static int findSubarrayORSumv1(List<Integer> input) {
        int len = input.size();
        int orSum = 0;
        int bitwiseORElements = 0;
        int mod = 1000000007;
        for (int i = 0; i < len; i++) {
            bitwiseORElements = 0;
            for (int j = i; j < len; j++) {
                bitwiseORElements = bitwiseORElements | input.get(j);
                orSum += bitwiseORElements;
                orSum %= mod;
            }
        }
        return orSum % mod;
    }

    private static int findSubarrayORSumv2(List<Integer> input) {
        int len = input.size();
        long total_subarrays = (long) len * (len + 1) / 2;
        int mod = 1000000007;
        long final_ans = 0;
        for (int i = 0; i < 31; i++) {
            long consecutive_zeros = 0;
            long count_subarray_with_zeros = 0;
            for (int num : input) {
                if (checkBit(num, i)) {
                    consecutive_zeros += 1;
                } else {
                    count_subarray_with_zeros += (consecutive_zeros * (consecutive_zeros + 1) / 2);
                    consecutive_zeros = 0;
                }
            }
            count_subarray_with_zeros += (consecutive_zeros * (consecutive_zeros + 1) / 2);
            long count_subarray_with_one = total_subarrays - count_subarray_with_zeros;
            final_ans += count_subarray_with_one * (1 << i);
        }
        return (int) (final_ans % mod);
    }

    public static boolean checkBit(int N, int j) {
        return (N & (1 << j)) == 0;
    }

    public int solve(int[] A) {//1 2 3 4 5
        int n = A.length;
        int[] idx = new int[32];//
        long ans = 0;
        for (int i = 1; i <= n; ++i) {//1, 2thbased indexing
            long tmp = A[i - 1];//1,2
            for (int j = 0; j <= 31; ++j) {//0,1 ,2
                long pw = 1 << j;//1<<0=1, 1<<1=10=2, 1<<2=100=4
                if ((tmp & pw) != 0) { //1 & 1 =1 , 2 & 1 =0  ,2&4=0 ,        //if jth bit is set
                    ans += pw * i; //ans=0+1    // add its contribution in ans for all subarrays ending at index i
                    idx[j] = i;//1 // store the index f
                } else if (idx[j] != 0) // idx[1]=2=10          //if jth bit is not set
                {
                    ans += pw * idx[j]; // 1+=2*2=5        // add its contribution in ans for all subarrays ending at index i using
                } // the information of last element having jth bit set
            }
        }
        return (int) (ans % 1000000007);
    }

}
