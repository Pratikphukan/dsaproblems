package com.dsaproblems.DSAProblems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}}; //In Java, array initializers like this can only be used at the point of declaration, not for assignment
        int[][] merged = merge(intervals);
        System.out.println(Arrays.deepToString(merged));

        intervals = new int[][]{{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        merged = insert(intervals, newInterval);
        System.out.println(Arrays.deepToString(merged));
    }

    //working code
    private static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        ArrayList<int[]> mergedIntervals = new ArrayList<>();
        int i = 0;
        for (; i < n && intervals[i][1] < newInterval[0]; i++) {
            mergedIntervals.add(intervals[i]);
        }
        for (; i < n && intervals[i][0] <= newInterval[1]; i++) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        }
        mergedIntervals.add(newInterval);
        for (; i < n; i++) {
            mergedIntervals.add(intervals[i]);
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    //working code
    private static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (intervalA, intervalB) -> intervalA[0] - intervalB[0]);
        ArrayList<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0])
                merged.add(interval);
            else
                merged.get(merged.size() - 1)[1] = Math.max(
                        merged.get(merged.size() - 1)[1],
                        interval[1]
                );
        }
        return merged.toArray(new int[merged.size()][]);
    }
}

