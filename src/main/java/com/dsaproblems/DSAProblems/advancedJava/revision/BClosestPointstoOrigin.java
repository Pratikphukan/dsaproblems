package com.dsaproblems.DSAProblems.advancedJava.revision;

import com.dsaproblems.DSAProblems.heap01.Coordinates;

import java.util.*;

public class BClosestPointstoOrigin {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 3)));
        input.add(new ArrayList<>(Arrays.asList(-2, 2)));
        int B = 2;
        System.out.println(findClosestPointToOriginv1(input, B));
        System.out.println(findClosestPointToOriginv2(input, B));
    }

    private static ArrayList<ArrayList<Integer>> findClosestPointToOriginv2(List<List<Integer>> input, int B) {
        Queue<Coordinates> min_heap = new PriorityQueue<>((c1, c2) -> Long.compare(c1.getDistance(), c2.getDistance()));
        for (List<Integer> integers : input) {
            min_heap.add(new Coordinates(integers.get(0), integers.get(1)));
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            Coordinates currMin = min_heap.poll();
            ans.add(new ArrayList<>(Arrays.asList(currMin.getX(), currMin.getY())));
        }
        return ans;
    }

    private static ArrayList<ArrayList<Integer>> findClosestPointToOriginv1(List<List<Integer>> input, int B) {
        Queue<Coordinates> min_heap = new PriorityQueue<>();
        for (List<Integer> integers : input) {
            min_heap.add(new Coordinates(integers.get(0), integers.get(1)));
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Coordinates currMin = null;
        for (int i = 0; i < B; i++) {
            currMin = min_heap.poll();
            ans.add(new ArrayList<>(Arrays.asList(currMin.getX(), currMin.getY())));
        }
        return ans;
    }

}
