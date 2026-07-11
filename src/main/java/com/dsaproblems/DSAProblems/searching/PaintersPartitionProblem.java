package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PaintersPartitionProblem {

    private static final Integer mod = 10000003;

    public static void main(String[] args) {
        // 1, 8, 11, 3|3|1
        // 1000000, 1000000|1|1000000
        // 185,186,938,558,655,461,441,234,902,681|3|10
        // 884,228,442,889|4|10
        List<Integer> A = new ArrayList<>(Arrays.asList(185, 186, 938, 558, 655, 461, 441, 234, 902, 681));
        int painters = 3;
        int timeByEachPainter = 10;
        System.out.println(findMinimumTimeToPaintAllBoards(A, painters, timeByEachPainter));
        System.out.println(findMinimumTimeToPaintAllBoardsv1(A, painters, timeByEachPainter));
        System.out.println(findMinimumTimeToPaintAllBoardsv2(A, painters, timeByEachPainter));
    }

    //working code
    private static int findMinimumTimeToPaintAllBoardsv2(List<Integer> A, int painters, int timeByEachPainter) {
        long sumLen = 0L, maxLen = 0L;
        for (int len : A) {
            maxLen = Math.max(maxLen, len);
            sumLen += len;
        }
        if (painters >= A.size()) return (int) ((maxLen * timeByEachPainter) % 10000003);
        if (painters == 1) return (int) ((sumLen * timeByEachPainter) % 10000003);
        long minTime = maxLen * timeByEachPainter, maxTime = sumLen * timeByEachPainter;
        long ans = maxTime;
        while (minTime <= maxTime) {// search space is time here
            long threshold = minTime + (maxTime - minTime) / 2;
            if (canBeAllotedv1(A, painters, timeByEachPainter, threshold)) {
                ans = threshold;
                maxTime = threshold - 1;
            } else {
                minTime = threshold + 1;
            }
        }
        return (int) (ans % 10000003);
    }

    //A->painters, B->time by each painter
    private static boolean canBeAllotedv2(List<Integer> A, int painters, int timeByEachPainter, long threshold) {
        int n = A.size();
        int index = 0;
        for (int i = 0; i < painters && index < n; i++) {
            long total = 0L;
            while (total < threshold && index < n) {
                total += (long) A.get(index) * timeByEachPainter;
                if (total > threshold) {
                    break;
                }
                index++;
            }
        }
        return index == n;
    }

    private static boolean canBeAlloted(int painters, List<Integer> A, long possibleTime) {
        long board_len = 0L;
        int painter_count = 1;
        for (int i = 0; i < A.size(); i++) {
            board_len += A.get(i);
            if (board_len > possibleTime) {
                board_len = 0;
                painter_count++;
                i--;
                if (painter_count > painters) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canBeAllotedv1(List<Integer> A, int painters, int timeByEachPainter, long possibleTime) {
        // timeByEachPainter should be 1 here
        long board_len = 0L;
        int painter_count = 1;
        for (int i = 0; i < A.size(); i++) {
            board_len += A.get(i); // No multiplication
            if (board_len * timeByEachPainter > possibleTime) {
                painter_count++;
                if (painter_count > painters) {
                    return false;
                }
                board_len = 0;
                i--;
            }
        }
        return true;
    }

    // working code
    private static int findMinimumTimeToPaintAllBoardsv1(List<Integer> A, int painters, int timeByEachPainter) {
        long maxLen = 0L, sumLen = 0L;
        for (int len : A) {
            maxLen = Math.max(maxLen, len);
            sumLen += len;
        }
        if (painters >= A.size()) return (int) ((maxLen * timeByEachPainter) % 10000003);
        if (painters == 1) return (int) ((sumLen * timeByEachPainter) % 10000003);
        long ans = maxLen * timeByEachPainter;
        while (maxLen <= sumLen) {
            long threshold = maxLen + (sumLen - maxLen) / 2;
            if (canBeAlloted(painters, A, threshold)) {
                ans = threshold;
                sumLen = threshold - 1;
            } else {
                maxLen = threshold + 1;
            }
        }
        return (int) ((ans * timeByEachPainter) % 10000003);
    }

    private static int findMinimumTimeToPaintAllBoards(List<Integer> A, int painters, int timeByEachPainter) {
        List<Long> durations = A.stream().map(x -> x.longValue() * timeByEachPainter).collect(Collectors.toList());
        long minTime = 0L;
        long maxTime = 0L;
        for (Long reqTime : durations) {
            minTime = Math.max(minTime, reqTime);// min time reqd to paint a board
            maxTime += reqTime;// max time to paint all boards
        }
        if (painters >= durations.size()) {
            return (int) minTime;
        }
        Long ans = maxTime;
        if (painters == 1) {
            ans = maxTime % mod.longValue();
            return ans.intValue();
        }
        long possibleTime;
        while (minTime <= maxTime) {
            possibleTime = minTime + (maxTime - minTime) / 2;
            if (findPossibleWorkers(durations, possibleTime) <= painters) {
                ans = possibleTime;
                maxTime = possibleTime - 1;
            } else {
                minTime = possibleTime + 1;
            }
        }
        return ans.intValue() % mod;
    }

    private static int findPossibleWorkers(List<Long> durations, long possibleTime) {
        long sum = 0l;
        int count = 0;
        for (Long reqTime : durations) {
            sum += reqTime;
            if (sum > possibleTime) {
                count++;
                sum = reqTime;
            }
        }
        if (sum > 0) {
            count++;
        }
        return count;
    }

}
