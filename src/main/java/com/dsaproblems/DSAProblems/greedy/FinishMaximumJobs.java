package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FinishMaximumJobs {

    public static void main(String[] args) {
//		List<Integer> A = new ArrayList<>(Arrays.asList(1, 5, 7, 1));
//		List<Integer> B = new ArrayList<>(Arrays.asList(7, 8, 8, 8));

        List<Integer> A = new ArrayList<>(Arrays.asList(3, 2, 6));
        List<Integer> B = new ArrayList<>(Arrays.asList(9, 8, 9));

//		List<Integer> A = new ArrayList<>(Arrays.asList(4, 4, 8, 15, 6));
//		List<Integer> B = new ArrayList<>(Arrays.asList(9, 5, 15, 16, 7));

        System.out.println(getMaxJobsDoneInGivenTimev1(A, B));
        System.out.println(getMaxJobsDoneInGivenTimev2(A, B));
        System.out.println(getMaxJobsDoneInGivenTimev3(A, B));
        System.out.println(getMaxJobsDoneInGivenTimev4(A, B));
        System.out.println(getMaxJobsDoneInGivenTimev5(A, B));
    }

    private static int getMaxJobsDoneInGivenTimev5(List<Integer> A, List<Integer> B) {
        List<int[]> startFinishPairs = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            startFinishPairs.add(new int[]{A.get(i), B.get(i)});
        }
        Collections.sort(startFinishPairs, (a, b) -> a[1] - b[1]);
        int endTimeOfLastEntry = 0;
        int maxJobsDone = 0;
        for (int[] pair : startFinishPairs) {
            if (pair[0] >= endTimeOfLastEntry) {
                maxJobsDone += 1;
                endTimeOfLastEntry = pair[1];
            }
        }
        return maxJobsDone;
    }

    // working code
    private static int getMaxJobsDoneInGivenTimev4(List<Integer> A, List<Integer> B) {
        List<StartFinishPairII> startFinishPairs = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            startFinishPairs.add(new StartFinishPairII(A.get(i), B.get(i)));
        }
        Collections.sort(startFinishPairs, (a, b) -> a.getEndTime() - b.getEndTime());
        int endTimeOfLastEntry = 0;
        int maxJobsDone = 0;
        for (StartFinishPairII startFinishPair : startFinishPairs) {
            if (startFinishPair.getStartTime() >= endTimeOfLastEntry) {
                maxJobsDone += 1;
                endTimeOfLastEntry = startFinishPair.getEndTime();
            }
        }
        return maxJobsDone;
    }

    // working code
    private static int getMaxJobsDoneInGivenTimev3(List<Integer> A, List<Integer> B) {
        List<StartFinishPairI> startFinishPairs = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            startFinishPairs.add(new StartFinishPairI(A.get(i), B.get(i)));
        }
        Collections.sort(startFinishPairs);
        int endTimeOfLastEntry = 0;
        int maxJobsDone = 0;
        for (StartFinishPairI startFinishPair : startFinishPairs) {
            if (startFinishPair.getStartTime() >= endTimeOfLastEntry) {
                maxJobsDone += 1;
                endTimeOfLastEntry = startFinishPair.getEndTime();
            }
        }
        return maxJobsDone;
    }

    // working code
    private static int getMaxJobsDoneInGivenTimev2(List<Integer> A, List<Integer> B) {
        Map<Integer, List<Integer>> finishToStartMap = new TreeMap<>();
        List<Integer> startTimesForEveryEnd = null;
        for (int i = 0; i < B.size(); i++) {
            if (!finishToStartMap.containsKey(B.get(i))) {
                startTimesForEveryEnd = new ArrayList<>();
                startTimesForEveryEnd.add(A.get(i));
                finishToStartMap.put(B.get(i), startTimesForEveryEnd);
            } else {
                startTimesForEveryEnd = finishToStartMap.get(B.get(i));
                startTimesForEveryEnd.add(A.get(i));
            }
        }
        Integer finishTimeForLastEntry = null;
        int maxJobsDone = 0;
        startTimesForEveryEnd = null;
        for (Map.Entry<Integer, List<Integer>> entry : finishToStartMap.entrySet()) {
            if (finishTimeForLastEntry == null) {
                maxJobsDone += 1;
                finishTimeForLastEntry = entry.getKey();
            } else {
                for (Integer startTimeForIthEndTime : entry.getValue()) {
                    if (startTimeForIthEndTime >= finishTimeForLastEntry) {
                        maxJobsDone += 1;
                        finishTimeForLastEntry = entry.getKey();
                        break;
                    }
                }
            }
        }
        return maxJobsDone;
    }

    // obj.compareTo(T anotherObj)->compare(obj, anotherObj)->obj is small: -1, obj
    // is same: 0, obj is greater: 1
    private static int getMaxJobsDoneInGivenTimev1(List<Integer> A, List<Integer> B) {
//		int startTime = A.stream().reduce(Integer.MAX_VALUE, (acc, curr) -> acc.compareTo(curr) < 0 ? acc : curr);
        int lastEndTime = B.stream().reduce(1, (acc, curr) -> acc.compareTo(curr) > 0 ? acc : curr);
        Map<Integer, Set<Integer>> finishToStartMap = new TreeMap<>();
        Set<Integer> set = null;
        for (int i = 0; i < B.size(); i++) {
            if (!finishToStartMap.containsKey(B.get(i))) {
                set = new HashSet<>();
                set.add(A.get(i));
                finishToStartMap.put(B.get(i), set);
            } else {
                set = finishToStartMap.get(B.get(i));
                set.add(A.get(i));
            }

        }
        Integer finishTimeForLastEntry = null;
        int maxJobsDone = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : finishToStartMap.entrySet()) {
            set = entry.getValue();
            if (finishTimeForLastEntry == null || set.contains(finishTimeForLastEntry)) {
                maxJobsDone += 1;
            } else {
                for (int startTimeForEntry = finishTimeForLastEntry
                        + 1; startTimeForEntry <= lastEndTime; startTimeForEntry++) {
                    if (set.contains(startTimeForEntry)) {
                        maxJobsDone += 1;
                        break;
                    }
                }
            }
            finishTimeForLastEntry = entry.getKey();
        }
        return maxJobsDone;
    }

}
