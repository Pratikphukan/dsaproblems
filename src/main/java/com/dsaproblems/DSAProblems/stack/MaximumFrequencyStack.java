package com.dsaproblems.DSAProblems.stack;

import java.util.*;

import static com.dsaproblems.DSAProblems.stack.MaximumFrequencyStackV1.performActionsv2;

public class MaximumFrequencyStack {

    private static int maxFreq = 0;

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        //input1
//        A.add(new ArrayList<>(Arrays.asList(1, 5)));
//        A.add(new ArrayList<>(Arrays.asList(1, 7)));
//        A.add(new ArrayList<>(Arrays.asList(1, 5)));
//        A.add(new ArrayList<>(Arrays.asList(1, 7)));
//        A.add(new ArrayList<>(Arrays.asList(1, 4)));
//        A.add(new ArrayList<>(Arrays.asList(1, 5)));
//        A.add(new ArrayList<>(Arrays.asList(2, 0)));
//        A.add(new ArrayList<>(Arrays.asList(2, 0)));
//        A.add(new ArrayList<>(Arrays.asList(2, 0)));
//        A.add(new ArrayList<>(Arrays.asList(2, 0)));

        //input2
//        A.add(new ArrayList<>(Arrays.asList(1, 4)));
//        A.add(new ArrayList<>(Arrays.asList(1, 5)));
//        A.add(new ArrayList<>(Arrays.asList(1, 4)));
//        A.add(new ArrayList<>(Arrays.asList(2, 0)));
//        A.add(new ArrayList<>(Arrays.asList(2, 0)));


        //input3
        A.add(new ArrayList<>(Arrays.asList(1, 9)));
        A.add(new ArrayList<>(Arrays.asList(1, 6)));
        A.add(new ArrayList<>(Arrays.asList(2, 0)));
        A.add(new ArrayList<>(Arrays.asList(2, 0)));
        A.add(new ArrayList<>(Arrays.asList(1, 1)));
        System.out.println(performActionsv2(A));
    }

    //working code
    private static ArrayList<Integer> performActionsv1(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Deque<Integer>> groupMap = new HashMap<>();
        for (ArrayList<Integer> action : A) {
            int op = action.get(0);
            int val = action.get(1);
            if (op == 1) {
                result.add(insert(val, freqMap, groupMap));
            } else if (op == 2) {
                result.add(pop(freqMap, groupMap));
            }
        }
        return result;
    }

    //HashMap/LinkedHashMap: allow one null key and multiple null values
    //ArrayDeque.pollFirst() / poll() returns null when the deque is empty
    private static Integer pop(Map<Integer, Integer> freqMap,
                               Map<Integer, Deque<Integer>> groupMap) {
        Deque<Integer> stack = groupMap.get(maxFreq);
        if (stack == null || stack.isEmpty()) return null;
        int x = stack.pollFirst();
        freqMap.put(x, freqMap.get(x) - 1);
        if (stack.isEmpty()) {
            maxFreq--;
        }
        return x;
    }

    private static int insert(int val,
                              Map<Integer, Integer> freqMap,
                              Map<Integer, Deque<Integer>> groupMap) {
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);
        maxFreq = Math.max(maxFreq, freq);
        groupMap.putIfAbsent(freq, new ArrayDeque<>());
        groupMap.get(freq).addFirst(val);
        return -1;
    }
}
