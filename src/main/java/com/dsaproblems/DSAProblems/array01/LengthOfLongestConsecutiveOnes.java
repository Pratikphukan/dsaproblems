package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LengthOfLongestConsecutiveOnes {

    public static void main(String[] args) {
        String str = "111000";
        //0111111111
        //1110111001
        //11010110000000000
        String str1 = "1110111001";
        String str2 = "11010110000000000"; // "01110110110", "010100110101", very imp->"00111011100", "01110"
        System.out.println(maxLengthConsecutiveOnesv1(str1));
        System.out.println(maxLengthConsecutiveOnesv4(str1));
        System.out.println(maxLengthConsecutiveOnesv5(str2));
        System.out.println(maximum_one(str2));
    }

    // working code, best and optimal
    //This approach uses O(1) space and O(n) time
    private static int maxLengthConsecutiveOnesv5(String A) {
        int len = A.length();
        int totalOnes = 0, maxLen = 0, curr = 0;
        for (char c : A.toCharArray()) {
            if (c == '1') totalOnes += 1; //Count total 1s in the string
        }
        if (totalOnes == len) return len;
        int i = 0;
        while (i < len) {
            if (A.charAt(i) == '1') {
                int count = 0;
                while (i < len && A.charAt(i) == '1') { //Traverse the string, tracking the length of the current block of 1s (curr)
                    count++;
                    i++;
                }
                curr = count;
            } else {
                //For each 0, count consecutive 1s to its left (curr) and right, sum them,
                // and if the sum is less than total 1s, add 1
                int left = curr, right = 0, j = i + 1;
                while (j < len && A.charAt(j) == '1') {
                    right++;
                    j++;
                }
                int sum = left + right;
                if (sum < totalOnes) sum++;
                maxLen = Math.max(maxLen, sum);
                curr = 0;
                i++;
            }
        }
        return maxLen;
    }

    static int maximum_one(String s) {
        int cnt_one = 0;
        int n = s.length();
        int max_cnt = 0, temp = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                cnt_one++;
                temp++;
            } else {
                max_cnt = Math.max(max_cnt, temp);
                temp = 0;
            }
        }
        max_cnt = Math.max(max_cnt, temp);

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = (s.charAt(0) == '1') ? 1 : 0;
        right[n - 1] = (s.charAt(n - 1) == '1') ? 1 : 0;

        for (int i = 1; i < n; i++) {
            left[i] = (s.charAt(i) == '1') ? left[i - 1] + 1 : 0;
        }

        for (int i = n - 2; i >= 0; i--) {
            right[i] = (s.charAt(i) == '1') ? right[i + 1] + 1 : 0;
        }

        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                int sum = left[i - 1] + right[i + 1];

                if (sum < cnt_one)
                    max_cnt = Math.max(max_cnt, sum + 1);
                else
                    max_cnt = Math.max(max_cnt, sum);
            }
        }
        return max_cnt;
    }

    // working code, less optimal
    private static int maxLengthConsecutiveOnesv4(String A) {
        int len = A.length();
        List<Integer> prefix = new ArrayList<>(Collections.nCopies(len, 0));
        List<Integer> suffix = new ArrayList<>(Collections.nCopies(len, 0));
        prefix.set(0, A.charAt(0) - '0');
        suffix.set(len - 1, A.charAt(len - 1) - '0');
        int countOnes = (A.charAt(0) - '0') == 1 ? 1 : 0;
        for (int i = 1; i < len; i++) {
            int num = A.charAt(i) - '0';
            prefix.set(i, num == 0 ? 0 : num + prefix.get(i - 1));
            countOnes += (num == 1) ? 1 : 0;
        }
        for (int i = len - 2; i >= 0; i--) {
            int num = A.charAt(i) - '0';
            suffix.set(i, num == 0 ? 0 : num + suffix.get(i + 1));
        }
        if (countOnes == A.length()) {
            return countOnes;
        }
        int max_count = 0;
        for (int i = 0; i < A.length(); i++) {
            max_count = Math.max(max_count, Math.max(prefix.get(i), suffix.get(i))); //in the existing string, find the max length of ones
        }
        for (int i = 1; i < len - 1; i++) {
            if (A.charAt(i) == '0') {
                int count = prefix.get(i - 1) + suffix.get(i + 1);
                if (countOnes > count) {
                    count += 1;
                }
                max_count = Math.max(max_count, count);
            }
        }
        return max_count;
    }

    //working code, better than maxLengthConsecutiveOnesv4
    //efficient at O(n) time and O(n) space, using prefix and suffix arrays
    private static int maxLengthConsecutiveOnesv1(String A) {
        int len = A.length();
        int[] prefix = new int[len];
        int[] suffix = new int[len];
        prefix[0] = A.charAt(0) - '0';
        suffix[len - 1] = A.charAt(len - 1) - '0';
        int countOnes = A.charAt(0) == '1' ? 1 : 0;
        for (int i = 1; i < len; i++) {
            int num = A.charAt(i) - '0';
            prefix[i] = num == 0 ? 0 : num + prefix[i - 1];
            countOnes += num == 1 ? 1 : 0;
        }
        for (int i = len - 2; i >= 0; i--) {
            int num = A.charAt(i) - '0';
            suffix[i] = num == 0 ? 0 : num + suffix[i + 1];
        }
        if (countOnes == A.length()) {
            return countOnes;
        }
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (A.charAt(i) == '0') {
                count += i == 0 ? 0 : prefix[i - 1];
                count += i == (len - 1) ? 0 : suffix[i + 1];
                if (countOnes > count) {
                    count += 1;
                }
                if (count > max) {
                    max = count;
                }
            }
            count = 0;
        }
        return max;
    }

//    private static int maxLengthConsecutiveOnesv1(String input) {
//        List<Integer> prefix = new ArrayList<>();
//        List<Integer> suffix = new ArrayList<>();
//        int len = input.length();
//        prefix.add(input.charAt(0) - '0');
//        suffix.add(input.charAt(len - 1) - '0');
//        int countOnes = 0;
//        Integer item = null;
//        for (int i = 1; i < len; i++) {
//            item = input.charAt(i) - '0';
//            if (item.equals(0)) {
//                prefix.add(0);
//            } else {
//                prefix.add(item + prefix.get(i - 1));
//                countOnes += 1;
//            }
//        }
//        for (int i = len - 2; i >= 0; i--) {
//            item = input.charAt(i) - '0';
//            if (item.equals(0)) {
//                suffix.add(0, item);
//            } else {
//                suffix.add(0, item + suffix.get(0));
//            }
//        }
//        if (countOnes == input.length()) {
//            return countOnes;
//        }
//        int max = Integer.MIN_VALUE;
//        int count = 0;
//        for (int i = 0; i < len; i++) {
//            if (input.charAt(i) == '0') {
//                count = count + (i == 0 ? 0 : prefix.get(i - 1));
//                count = count + (i == (len - 1) ? 0 : suffix.get(i + 1));
//                if (countOnes > count) {
//                    count += 1;
//                }
//                if (count > max) {
//                    max = count;
//                }
//            }
//            count = 0;
//        }
//        return max;
//    }

}
