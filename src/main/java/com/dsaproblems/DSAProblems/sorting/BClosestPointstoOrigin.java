package com.dsaproblems.DSAProblems.sorting;

import java.util.*;

public class BClosestPointstoOrigin {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class PointComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            int d1 = p1.x * p1.x + p1.y * p1.y;
            int d2 = p2.x * p2.x + p2.y * p2.y;
            return Integer.compare(d1, d2);
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 3)));
        input.add(new ArrayList<>(Arrays.asList(-2, 2)));
        int B = 1;
        System.out.println(findClosestPointToOriginv1(input, B));
    }

    private static ArrayList<ArrayList<Integer>> findClosestPointToOriginv1(List<List<Integer>> A, int B) {
        ArrayList<Point> arr = new ArrayList<>();
        for (List<Integer> point : A) {
            arr.add(new Point(point.get(0), point.get(1)));
        }
        Collections.sort(arr, new PointComparator());
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            int x = arr.get(i).x;
            int y = arr.get(i).y;
            ArrayList<Integer> row = new ArrayList<>();
            row.add(x);
            row.add(y);
            ans.add(row);
        }
        return ans;
    }
}
