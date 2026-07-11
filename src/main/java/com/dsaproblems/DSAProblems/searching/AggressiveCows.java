package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AggressiveCows {

    public static void main(String[] args) {
        List<Integer> coordinates = new ArrayList<>(
                Arrays.asList(82, 61, 38, 88, 12, 7, 6, 12, 48, 8, 31, 90, 35, 5, 88, 2, 66, 19, 5, 96, 84, 95));
        //2,6,11,14,19,25,30,39,43->4
        // 82,61,38,88,12,7,6,12,48,8,31,90,35,5,88,2,66,19,5,96,84,95->8
        // 1, 2, 3, 4, 5->3
        int cows = 8;
        System.out.println(findLargestMinimumDistanceBetweenCows(coordinates, cows));
    }

    // working code
    public int solve(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        int l = Integer.MAX_VALUE;
        for (int i = 1; i < A.size(); i++) {
            l = Math.min(l, A.get(i) - A.get(i - 1)); // considering the locations are in ascending order
            // min = Math.min(min, A.get(i));
            // max = Math.max(max, A.get(i));
        }
        int h = A.get(A.size() - 1) - A.get(0);
        int ans = h;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (isPossible(A, mid, B)) {
                ans = mid;
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return ans;
    }

    public boolean isPossible(ArrayList<Integer> A, int dist, int B) {
        int lastcow = A.get(0), count = 1;
        for (int i = 1; i < A.size(); i++) {
            if (A.get(i) - lastcow >= dist) {
                lastcow = A.get(i);
                count++;
                if (count == B) {
                    return true;
                }
            }
        }
        return false;
    }

    //working code
    private static int findLargestMinimumDistanceBetweenCows(List<Integer> points, int cows) {
        Collections.sort(points);
        int noOfPoints = points.size();
        int maxPossibleMinSep = points.get(noOfPoints - 1) - points.get(0); // considering two cows only
        int minPossibleMinSep = points.get(1) - points.get(0); // considering the number of cows as the size of the array
        //find the min adjacent difference between two stalls
        for (int i = 1; i < noOfPoints - 1; i++) {
            minPossibleMinSep = Math.min(minPossibleMinSep, points.get(i + 1) - points.get(i));
        }
        int ans = maxPossibleMinSep;
        int possibleLargestMinimunSeparation = 0;
        while (minPossibleMinSep <= maxPossibleMinSep) {
            possibleLargestMinimunSeparation = minPossibleMinSep + (maxPossibleMinSep - minPossibleMinSep) / 2;
            if (findPossibleCows(points, possibleLargestMinimunSeparation, cows)) {
                ans = possibleLargestMinimunSeparation;
                minPossibleMinSep = possibleLargestMinimunSeparation + 1;
            } else {
                maxPossibleMinSep = possibleLargestMinimunSeparation - 1;
            }
        }
        return ans;
    }

    private static boolean findPossibleCows(List<Integer> coordinates, int possibleLargestMinimunSeparation, int cows) {
        int lastCow = coordinates.get(0);
        int count = 1;
        for (int i = 1; i < coordinates.size(); i++) {
            if (coordinates.get(i) - lastCow >= possibleLargestMinimunSeparation) {
                lastCow = coordinates.get(i);
                count++;
            }
            if (count > cows) return true;
        }
        return count == cows;
    }
}
