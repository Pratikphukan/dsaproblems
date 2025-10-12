package com.dsaproblems.DSAProblems.advancedJava.revision;

import lombok.ToString;

import java.util.ArrayList;


public class MergeIntervals {

    @ToString
    static class Interval {
        int start;
        int end;

        Interval() {
            this.start = 0;
            this.end = 0;
        }

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        ArrayList<Interval> intervals = new ArrayList<>();
//        intervals.add(new Interval(3, 4));
//        intervals.add(new Interval(6, 9));
//        Interval newInterval = new Interval(1, 2);

        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(6, 9));
        intervals.add(new Interval(12, 16));
        Interval newInterval = new Interval(2, 13);

//        System.out.println(insertv1(intervals, newInterval));
//        System.out.println(insertv2(intervals, newInterval));
        System.out.println(insertv3(intervals, newInterval));
    }

    //working code
    private static ArrayList<Interval> insertv3(ArrayList<Interval> intervals, Interval newInterval) {
        int n = intervals.size();
        ArrayList<Interval> mergedIntervals = new ArrayList<>();
        int i = 0;
        for (; i < n && intervals.get(i).end < newInterval.start; i++) {
            mergedIntervals.add(intervals.get(i));
        }
        for (; i < n && intervals.get(i).start <= newInterval.end; i++) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
        }
        mergedIntervals.add(newInterval);
        for (; i < n; i++) {
            mergedIntervals.add(intervals.get(i));
        }
        return mergedIntervals;
    }

    //Time complexity is O(n)
    private static ArrayList<Interval> insertv2(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> mergedIntervals = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) { //Add all intervals ending before the new interval starts (no overlap).
            mergedIntervals.add(intervals.get(i));
            i++;
        }
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) { //Merge all overlapping intervals with the new interval (update start/end).
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }
        mergedIntervals.add(newInterval); //Add the merged new interval.

        while (i < intervals.size()) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }
        return mergedIntervals;
    }

    private static ArrayList<Interval> insertv1(ArrayList<Interval> intervals, Interval newInterval) {
        for (int i = 0; i < intervals.size(); i++) {
            if (i > 0 && i < intervals.size() - 1) {
                if (newInterval.start > intervals.get(i).end &&
                        newInterval.end < intervals.get(i + 1).start) {
                    intervals.add(i, newInterval);
                    break;
                }
            } else {
                if (i == 0 && newInterval.end < intervals.get(i).start) {
                    intervals.add(0, newInterval);
                    break;
                }
                if (i == intervals.size() - 1 && newInterval.start > intervals.get(i).end) {
                    intervals.add(0, newInterval);
                    break;
                }
            }
        }
        return new ArrayList<>();
    }
}
