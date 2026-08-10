package com.dsaproblems.DSAProblems.subarrays;

public class MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4, -9, 3, 9, 6, -8, 1};
        System.out.println(maxProductSubarrayv1(nums));
        System.out.println(maxProductSubarrayv2(nums));
        System.out.println(maxProductSubarrayv3(nums));
    }

    private static int maxProductSubarrayv3(int[] nums) {
        // Initialize maxProd as the first element, which represents the maximum product ending at the current index.
        int maxProd = nums[0];
        // Initialize minProd as the first element, which represents the minimum product ending at the current index.
        int minProd = nums[0];
        //It will store the final maximum product of any subarray.
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            if (current < 0) {
                if (maxProd > 0) maxProd = current;
                else maxProd = maxProd * current;
                if (minProd > 0) minProd = minProd * current;
                else minProd = current;
            } else {
                if (maxProd > 0) maxProd = maxProd * current;
                else maxProd = current;
                if (minProd > 0) minProd = current;
                else minProd = minProd * current;
            }
            result = Math.max(result, maxProd);
        }
        // Return the overall maximum product subarray.
        return result;
    }

    private static int maxProductSubarrayv2(int[] nums) {
        // Initialize maxProd as the first element, which represents the maximum product ending at the current index.
        int maxProd = nums[0];
        // Initialize minProd as the first element, which represents the minimum product ending at the current index.
        int minProd = nums[0];
        //It will store the final maximum product of any subarray.
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // Current number in the array.
            int current = nums[i];

            // If the current element is negative, swapping maxProd and minProd is required.
            // Because multiplying a negative number flips signs: a large positive product becomes negative,
            // and a small negative product might become the new maximum.
            if (current < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            // Calculate the new maxProd as the maximum among the current element and the product of maxProd with current.
            // This step decides whether to start a new subarray from current or continue with the product.
            maxProd = Math.max(current, maxProd * current);
            // Calculate the new minProd similarly. It's necessary to consider as a negative times a negative could yield a large positive.
            minProd = Math.min(current, minProd * current);

            // Update the result with the maximum product found so far.
            result = Math.max(result, maxProd);
        }
        // Return the overall maximum product subarray.
        return result;
    }

    private static int maxProductSubarrayv1(int[] nums) {
        int negativeCount = 0, len = nums.length;
        if (len == 1) return nums[0];
        int start = -1, end = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] < 0) {
                negativeCount++;
                if (start == -1) start = i;
                end = i;
            }
        }
        int ans = 1;
        if ((negativeCount & 1) == 0) {
            for (int num : nums) ans *= num;
        } else {
            int prod1 = 1, prod2 = 1;
            for (int i = start + 1; i < len; i++) prod1 *= nums[i];
            for (int i = 0; i < end; i++) prod2 *= nums[i];
            ans = Math.max(prod1, prod2);
        }
        return ans;
    }
}
