package com.dsaproblems.DSAProblems.TwoPointers01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnotherCountRectangles {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(5, 10, 20, 100, 105));
        int B = 110;
        System.out.println(countOfRectanglesv1(A, B));
        System.out.println(countOfRectanglesv2(A, B));
    }

    //not working
    private static int countOfRectanglesv2(List<Integer> input, int B) {
        int count = 0, mod = 1000000007;
        int left = 0, right = input.size() - 1;
        while (left < right) {
            long area = (long) input.get(left) * input.get(right);
            //This is because the array is sorted, so all elements from input[0] to
            // input[right] will form valid pairs with input[left] (since their products
            // will also be less than B). Thus, you add right + 1 to the count to include
            // all these valid pairs in one step, making the algorithm efficient.
            if (area < B) {
                count = (count % mod + (right + 1) % mod) % mod;
                left++;
            } else if (area > B) {
                right--;
            } else {
                //This is because the array is sorted, so all elements from input[left] to
                // input[right-1] will form valid pairs with input[left] (since their products
                // will also be less than B). Thus, you add right - left to the count to include
                // all these valid pairs in one step, making the algorithm efficient.
                // If area is equal to B, we can count all rectangles formed with the current left side
                count = (count % mod + (right - left) % mod) % mod;
                right--;
            }
        }
        return count;
    }


    //working code
    public int solve(int[] A, int B) {
        int n = A.length;
        long mod = 1000000007, count = 0;
        int l = 0, r = n - 1;
        while (l < n && r >= 0) {
            long area = (long) A[l] * A[r];
            if (area < B) {
                count = (count % mod + (r + 1) % mod) % mod;
                l++;
            } else {
                r--;
            }
        }
        return (int) (count % mod);
    }

    //not working, have to fix the code
    private static int countOfRectanglesv1(List<Integer> input, int B) {
        int count = 0, mod = 1000000007;
        int left = 0, right = input.size() - 1;
        while (left < right) {
            long area = (long) input.get(left) * input.get(right);
            if (area < B) {
                count = (count % mod + (right - left) % mod) % mod;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}
