package com.dsaproblems.DSAProblems.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TaskScheduling {

    public static void main(String[] args) {
        // 2, 3, 1, 5, 4|1, 3, 5, 4, 2
        List<Integer> currentState = new ArrayList<>(Arrays.asList(2, 3, 1, 5, 4));
        List<Integer> order = new ArrayList<>(Arrays.asList(1, 3, 5, 4, 2));
        System.out.println(findMinimumNoOfClockCyclesv1(currentState, order));
    }

    private static int findMinimumNoOfClockCyclesv1(List<Integer> currentState, List<Integer> order) {
        Deque<Integer> queue = new LinkedList<>();
        Integer movedTask = null;
        int totalCycles = 0;
        for (Integer taskState : currentState) {
            queue.offerLast(taskState);
        }
        for (Integer currOrder : order) {
            while (!currOrder.equals(queue.peekFirst())) {
                movedTask = queue.pollFirst();
                queue.offerLast(movedTask);
                totalCycles += 1;
            }
            queue.pollFirst(); // task is executed
            totalCycles += 1;
        }
        return totalCycles;
    }

}
