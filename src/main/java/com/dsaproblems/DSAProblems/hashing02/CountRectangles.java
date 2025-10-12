package com.dsaproblems.DSAProblems.hashing02;

import java.util.*;

public class CountRectangles {

    //Mark fields used in equals() and hashCode() as final
    //Do not provide setters.
    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3));
        List<Integer> B = new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1, 2));
        System.out.println(countRectanglesv1(A, B));
        System.out.println(countRectanglesv2(A, B));
    }

    //working code
    private static int countRectanglesv2(List<Integer> A, List<Integer> B) {
        int len = A.size();
        Set<Point> uniquePoints = new HashSet<>();
        for (int i = 0; i < len; i++) {
            uniquePoints.add(new Point(A.get(i), B.get(i)));
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            int x1 = A.get(i), y1 = B.get(i);
            for (int j = i + 1; j < len; j++) {
                int x2 = A.get(j), y2 = B.get(j);
                if (x1 != x2 && y1 != y2 &&
                        uniquePoints.contains(new Point(x1, y2)) &&
                        uniquePoints.contains(new Point(x2, y1))) {
                    // Only count rectangles in one direction to avoid duplicates
                    count++;
                }
            }
        }
        // Each rectangle is counted twice, so divide by 2
        return count / 2;
    }

    private static int countRectanglesv1(List<Integer> A, List<Integer> B) {
        int len = A.size();
        Set<Point> uniquePoints = new HashSet<>();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Point point = new Point(A.get(i), B.get(i));
            points.add(point);
            uniquePoints.add(point);
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < len; j++) {
                Point p2 = points.get(j);
                if (p1.x != p2.x && p1.y != p2.y &&
                        uniquePoints.contains(new Point(p1.x, p2.y)) &&
                        uniquePoints.contains(new Point(p2.x, p1.y))) {
                    count += 1;
                }
            }
            uniquePoints.remove(p1);
        }
        return count;
    }
}
