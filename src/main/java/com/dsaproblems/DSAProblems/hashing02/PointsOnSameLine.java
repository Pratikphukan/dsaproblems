package com.dsaproblems.DSAProblems.hashing02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointsOnSameLine {

    public static void main(String[] args) {
        // 4
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(-1, 0, 1, 2, 3, 3));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(1, 0, 1, 2, 3, 4));

        // 2
//		ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3, 1, 4, 5, 7, -9, -8, 6));
//		ArrayList<Integer> B = new ArrayList<>(Arrays.asList(4, -8, -3, -2, -1, 5, 7, -4));

//		ArrayList<Integer> A = new ArrayList<>(
//				Arrays.asList(-44, 44, 32, -43, -41, 18, -29, 49, 19, 28, 26, 29, 39, -4, 2, -36, 6, -45, -17, 16, -9,
//						34, 20, -48, -21, -19, -28, -45, -8, 8, 13, -42, -35, 15, -12, -36, 42, 24, 41));
//		ArrayList<Integer> B = new ArrayList<>(
//				Arrays.asList(40, -9, -31, 43, 33, -34, -40, 25, 32, -5, 34, 41, -11, 24, -31, 9, -27, -20, -40, 35,
//						-44, 29, 45, 40, 40, 33, -33, -20, -11, 20, -7, -28, 48, -44, 3, 39, 26, 21, 8));

//        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(48, 45, -3, 7, -25, 38, 2, 13, 13, 18, -44, 34, -27, -46,
//                48, -39, -41, -32, -16, 17, -6, 44, -28, -44, -6, -43, -16, 30, -3, -27, 32, 38, -26, 20, 4, -44, -37));
//        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(47, -42, 41, 22, -4, -35, -45, -22, 5, -20, 21, -13, 47,
//                32, -48, 47, 17, -23, 30, -30, 37, 42, 44, 23, 1, -40, -9, 34, -34, 49, 16, -35, 2, -19, 31, 23, -37));
        System.out.println(findPointsThatLieOnTheSameLine(A, B));

        System.out.println(findPointsThatLieOnTheSameLinev1(A, B));

        System.out.println(findPointsThatLieOnTheSameLinev2(A, B));
    }

    private static int findPointsThatLieOnTheSameLinev2(ArrayList<Integer> A, ArrayList<Integer> B) {
        int n = A.size();
        int maxPoints = 1;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicates = 0, verticals = 0, currMax = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int dx = A.get(j) - A.get(i);
                int dy = B.get(j) - B.get(i);
                if (dx == 0 && dy == 0) {
                    duplicates++;
                } else if (dx == 0) {
                    verticals++;
                    currMax = Math.max(currMax, verticals);
                } else {
                    int gcd = GCD(dx, dy);
                    dx /= gcd;
                    dy /= gcd;
                    String key = dx + "_" + dy;
                    slopeCount.put(key, slopeCount.getOrDefault(key, 0) + 1);
                    currMax = Math.max(currMax, slopeCount.get(key));
                }
            }
            maxPoints = Math.max(maxPoints, currMax + duplicates + 1);
        }
        return maxPoints;
    }

    private static int findPointsThatLieOnTheSameLinev1(ArrayList<Integer> A, ArrayList<Integer> B) {
        int max = 0;
        for (int i = 0; i < A.size(); i++) {
            HashMap<Float, Integer> map = new HashMap<>();
            int dup = 0;
            for (int j = 0; j < B.size(); j++) {
                if (i != j) {
                    if (A.get(i).equals(A.get(j)) && B.get(i).equals(B.get(j))) {
                        dup++;
                        continue;
                    }
                    float m = (B.get(j) - B.get(i) + 0f) / (A.get(j) - A.get(i));
                    map.put(m, map.getOrDefault(m, 0) + 1);
                    max = Math.max(max, map.get(m) + dup);
                }
            }
        }
        return max + 1;
    }

    private static Integer findPointsThatLieOnTheSameLine(ArrayList<Integer> A, ArrayList<Integer> B) {
        Integer currX = null;
        Integer currY = null;
        int noOfPoints = A.size();
        Integer numerator = null;
        Integer denominator = null;
        Integer gcd = null;
        Integer maxNoOfPoints = 1;
        Integer maxNoOfPointsEveryIteration = 1;
        List<Integer> slope = null;
        Map<List<Integer>, Integer> pointsWithSameSlope = null;
        for (int i = 0; i < noOfPoints; i++) {
            currX = A.get(i);
            currY = B.get(i);
            pointsWithSameSlope = new HashMap<>();
            for (int j = i + 1; j < noOfPoints; j++) {
                if (currX == A.get(j) && currY == B.get(j)) {
                    maxNoOfPointsEveryIteration += 1;
                } else {
                    gcd = GCD(currX - A.get(j), currY - B.get(j));
                    gcd = gcd != 0 ? gcd : 1;
                    numerator = (currX - A.get(j)) / gcd;
                    denominator = (currY - B.get(j)) / gcd;
                    slope = new ArrayList<>(Arrays.asList(numerator, denominator));
                    if (!pointsWithSameSlope.containsKey(slope)) {
                        pointsWithSameSlope.put(slope, 1);
                    } else {
                        maxNoOfPointsEveryIteration = Math.max(maxNoOfPointsEveryIteration,
                                pointsWithSameSlope.get(slope) + 1);
                        pointsWithSameSlope.put(slope, pointsWithSameSlope.get(slope) + 1);
                    }
                }
            }
            maxNoOfPoints = Math.max(maxNoOfPoints, maxNoOfPointsEveryIteration);
            maxNoOfPointsEveryIteration = 1;
        }
        return maxNoOfPoints + 1;
    }

    private static int GCD(int B, int C) {
        if (C == 0) {
            return B;
        }
        return GCD(C, B % C);
    }

}
