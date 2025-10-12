package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AggressiveCows {

    public static void main(String[] args) {
        List<Integer> coordinates = new ArrayList<>(
                Arrays.asList(82, 61, 38, 88, 12, 7, 6, 12, 48, 8, 31, 90, 35, 5, 88, 2, 66, 19, 5, 96, 84, 95));
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

    // not working, please check
    private static int findLargestMinimumDistanceBetweenCows(List<Integer> coordinates, int cows) {
        Collections.sort(coordinates);
        int noOfCoordinates = coordinates.size();
        int maxSeparation = coordinates.get(noOfCoordinates - 1) - coordinates.get(0); // considering two cows only
        int minSeparation = coordinates.get(1) - coordinates.get(0); // considering the number of cows as the size of
        // the array
        for (int i = 1; i < noOfCoordinates - 1; i++) {
            minSeparation = Math.max(minSeparation, coordinates.get(i + 1) - coordinates.get(i));
        }
        int ans = maxSeparation;
        int possibleLargestMinimunSeparation = 0;
        while (minSeparation <= maxSeparation) {
            possibleLargestMinimunSeparation = minSeparation + (maxSeparation - minSeparation) / 2;
            if (findCows(coordinates, possibleLargestMinimunSeparation) >= cows) {
                ans = possibleLargestMinimunSeparation;
                minSeparation = possibleLargestMinimunSeparation + 1;
            } else {
                maxSeparation = possibleLargestMinimunSeparation - 1;
            }
        }
        return ans;
    }

    private static int findCows(List<Integer> coordinates, int possibleLargestMinimunSeparation) {
        int lastCow = coordinates.get(0);
        int count = 1;
        for (int i = 1; i < coordinates.size(); i++) {
            if (coordinates.get(i) - lastCow >= possibleLargestMinimunSeparation) {
                lastCow = coordinates.get(i);
                count++;
            }
        }
        return count;
    }

}
