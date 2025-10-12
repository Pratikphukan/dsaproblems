package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountPairsWithGivenDifference {

    public static void main(String[] args) {

        List<Integer> input = new ArrayList<>(Arrays.asList(8, 12, 16, 4, 0, 20));
        //1,2->0
        //1,1,1,1,1,1,1,1,1,1->0
        // 8, 12, 16, 4, 0, 20->4
        // 1, 5, 3, 4, 2->3
        // 1, 1, 1, 2, 2->0////[1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 5, 5,
        // 5,
        // 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10]->3
        // 8,5,1,10,5,9,9,3,5,6,6,2,8,2,2,6,3,8,7,2,5,3,4,3,3,2,7,9,6,8,7,2,9,10,3,8,10,6,5,4,2,3->3
        int targetdifference = 4;
        System.out.println(findCountPairsWithGivenSumv1(input, targetdifference));
        System.out.println(findCountPairsWithGivenSumv2(input, targetdifference));
        System.out.println(findCountPairsWithGivenDiffv3(input, targetdifference));
        System.out.println(findCountPairsWithGivenDiffv4(input, targetdifference));

        /*
         * // Collections.sort(A); // int count = 0; // int l = 0, h = 1; //
         * while(h<A.size()){ // if((A.get(h)-A.get(l))==B){ // count++; // l++;h++; //
         * } // }
         *
         * // int count = 0; // Set<Integer> set = new HashSet<>(); //
         * A.stream().forEach(item -> set.add(item)); // for (Integer item : A) { // if
         * (set.contains(item - B)) { // count++; // } // if (set.contains(item + B)) {
         * // count++; // } // set.remove(item); // } // return count; int count = 0; //
         * Initialize count
         *
         * // Initialize empty hashmap. boolean[] hashmap = new boolean[MAX];
         *
         * // Insert array elements to hashmap for (int i = 0; i < A.size(); i++)
         * hashmap[A.get(i)] = true;
         *
         * for (int i = 0; i < A.size(); i++) { int x = A.get(i); if (x - B >= 0 &&
         * hashmap[x - B]) count++; if (x + B < MAX && hashmap[x + B]) count++;
         * hashmap[x] = false; } return count;
         */

    }

    //0<=A(i)<=10^5, get distinct pairs
    //working code
    private static int findCountPairsWithGivenDiffv4(List<Integer> input, int targetdifference) {
        Set<Integer> set = new HashSet<>(input);
        if (targetdifference == 0 && input.size() == set.size()) {
            return 0;
        }
        int count = 0;
        for (Integer num : input) {
            if (set.contains(targetdifference + num)) {
                count++;
                set.remove(targetdifference + num);
            }
        }
        return count;
    }

    //working code
    private static int findCountPairsWithGivenDiffv3(List<Integer> input, int targetdifference) {
        Collections.sort(input);
        int count = 0, left = 0, right = 0;
        while (right < input.size() && left <= right) {
            if (left == right) {
                right++;
                continue;
            }
            int diff = input.get(right) - input.get(left);
            if (diff < targetdifference) {
                right = skipDuplicates(input, right); // move right index to next distinct value to increase the difference
            } else if (diff > targetdifference) {
                left = skipDuplicates(input, left); // move left index to next distinct value to decrease the difference
            } else {
                count++;
                right = skipDuplicates(input, right); // move to the next right index
                left = skipDuplicates(input, left); // move to the next left index
            }
        }
        return count;
    }

    private static int skipDuplicates(List<Integer> input, int idx) {
        int current = input.get(idx);
        while (idx + 1 < input.size() && input.get(idx + 1) == current) {
            idx++;
        }
        return idx + 1; // move to the next distinct value
    }

    //get all distinct pairs
    private static int findCountPairsWithGivenSumv2(List<Integer> input, int targetdifference) {
        Collections.sort(input);
        int count = 0;
        int left = 0;
        int right = 0;
        while (right < input.size() && left <= right) {
            if (left == right) {
                right++;
            } else {
                if ((input.get(right) - input.get(left)) == targetdifference) {
                    count++;
                    right++;
                    while (right < input.size() && input.get(right) - input.get(right - 1) == 0) {
                        right++;
                    }
                } else if ((input.get(right) - input.get(left)) < targetdifference) {
                    right++;
                } else {
                    left++;
                    while (left <= right && input.get(left) - input.get(left - 1) == 0) {
                        left++;
                    }
                }
            }
        }
        return count;
    }

    //working code
    private static int findCountPairsWithGivenSumv1(List<Integer> input, int targetdifference) {
        Collections.sort(input);
        int count = 0;
        int left = 0;
        int right = 1;
        while (right < input.size() && left <= right) {
            if (left == right) {
                right++;
            } else {
                if ((input.get(right) - input.get(left)) == targetdifference) {
                    count++;
                    right++;
                    while (right < input.size() && input.get(right) - input.get(right - 1) == 0) {
                        right++;
                    }
                } else if ((input.get(right) - input.get(left)) < targetdifference) {
                    right++;
                } else {
                    left++;
                }
            }
        }
        return count;
    }

}
