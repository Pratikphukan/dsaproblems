package com.dsaproblems.DSAProblems.greedy;

import java.util.*;

public class FriendTravel {

    static long farthestVillage(long k, int[] A, int[] B) {
        Map<Long, Long> gifts = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            gifts.merge((long) A[i], (long) B[i], Long::sum);
        }
        List<Map.Entry<Long, Long>> list = new ArrayList<>(gifts.entrySet());
        list.sort(Map.Entry.comparingByKey());

        long reach = k; // farthest village reachable from 0
        for (var e : list) {
            long a = e.getKey(), b = e.getValue();
            if (a <= reach) reach += b;
            else break;
        }
        return reach;
    }

    public static void main(String[] args) {
        System.out.println(farthestVillage(3, new int[]{2, 5}, new int[]{1, 10})); // 4


        long k = 3;
        List<long[]> villages = java.util.List.of(
                new long[]{2, 1},
                new long[]{5, 10},
                new long[]{2, 8}
        );
        System.out.println(farthestVillage(k, villages)); // 4
        System.out.println(farthestVillagev1(k, villages));
    }

    private static long farthestVillagev1(long k, List<long[]> villages) {
        Map<Long, Long> gifts = new TreeMap<>();
        for (long[] v : villages) {
            gifts.put(v[0], gifts.getOrDefault(v[0], 0L) + v[1]);
        }
        long reach = k;
        for (Map.Entry<Long, Long> entry : gifts.entrySet()) {
            long a = entry.getKey(), b = entry.getValue();
            if (a <= reach) reach += b;
            else break;
        }
        return reach;
    }


    static long farthestVillage(long k, List<long[]> villages) {
        // merge gifts per village (Ai) and iterate in ascending order
        java.util.Map<Long, Long> gifts = new java.util.TreeMap<>();
        //for (long[] v : villages) gifts.merge(v[0], v[1], Long::sum);

        for (long[] v : villages) gifts.put(v[0], v[1]);

        long reach = k;
        for (var e : gifts.entrySet()) {
            long a = e.getKey(), b = e.getValue();
            if (a <= reach) reach += b;
            else break;
        }
        return reach;
    }
}
