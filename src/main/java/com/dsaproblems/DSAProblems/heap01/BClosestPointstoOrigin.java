package com.dsaproblems.DSAProblems.heap01;

import java.util.*;

public class BClosestPointstoOrigin {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 3)));
        input.add(new ArrayList<>(Arrays.asList(-2, 2)));
        int B = 2;
        System.out.println(findClosestPointToOriginv1(input, B));
        System.out.println(findClosestPointToOriginv2(input, B));
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(1, 3)));
        A.add(new ArrayList<>(Arrays.asList(-2, 2)));
        System.out.println(findClosestPointToOriginv3(A, B));
    }

    private static ArrayList<ArrayList<Integer>> findClosestPointToOriginv3(ArrayList<ArrayList<Integer>> A, int B) {
        Queue<int[]> maxHeap = new PriorityQueue<>(
                (point1, point2) -> Long.compare(
                        (long) point2[0] * point2[0] + (long) point2[1] * point2[1],
                        (long) point1[0] * point1[0] + (long) point1[1] * point1[1]));

        for (ArrayList<Integer> point : A) {
            if (maxHeap.size() < B) {
                maxHeap.add(new int[]{point.get(0), point.get(1)});
            } else {
                long currDist = (long) point.get(0) * point.get(0) +
                        (long) point.get(1) * point.get(1);
                long currMaxDist = (long) maxHeap.peek()[0] * maxHeap.peek()[0] +
                        (long) maxHeap.peek()[1] * maxHeap.peek()[1];
                if (currDist < currMaxDist) {
                    maxHeap.poll();
                    maxHeap.add(new int[]{point.get(0), point.get(1)});
                }
            }
        }

        List<int[]> list = new ArrayList<>(maxHeap);
        Collections.sort(list,
                (point1, point2) -> Integer.compare(
                        point1[0] * point1[0] + point1[1] * point1[1],
                        point2[0] * point2[0] + point2[1] * point2[1]));

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int[] ints : list) {
            ans.add(new ArrayList<>(Arrays.asList(ints[0], ints[1])));
        }
        return ans;
    }

    private static ArrayList<ArrayList<Integer>> findClosestPointToOriginv2(List<List<Integer>> input, int B) {
        Queue<Coordinates> min_heap = new PriorityQueue<>((c1, c2) -> Long.compare(c1.getDistance(), c2.getDistance()));
        for (List<Integer> point : input) {
            min_heap.add(new Coordinates(point.get(0), point.get(1)));
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
        for (List<Integer> point : input) {
            min_heap.add(new Coordinates(point.get(0), point.get(1)));
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
