package com.dsaproblems.DSAProblems.modulararithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Using a HashMap introduces overhead compared to an array because:


Lookup Time: Array access is O(1) via direct indexing, while HashMap involves hashing and possible collision resolution,
making it slower.
Memory Usage: HashMap stores keys, values, and additional metadata, consuming more memory than a simple array.
Boxing/Unboxing: With HashMap<Integer, Integer>, integers are boxed/unboxed, which adds CPU and memory overhead.
Garbage Collection: More objects are created with a HashMap, increasing GC pressure.
Arrays are faster and more memory-efficient for fixed-size, integer-indexed data.
 */
public class PairSumDivisibleByM {

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3, 4, 5); //all positive integers
        // (24,16,8,7,17,23,35,20,26,28,34,21),6
        // (1,2,3,4,5),2
        // (5, 17, 100, 11),28
        ArrayList<Integer> A = new ArrayList<>(a);
        int B = 2;
        System.out.println(possiblePairsv1(A, B));
        System.out.println(possiblePairsv2(A, B));
        System.out.println(possiblePairsv3(A, B));
    }

    //working code, faster than possiblePairsv1
    private static int possiblePairsv2(ArrayList<Integer> A, int B) {
        int[] count = new int[B];
        int mod = 1000000007;
        for (int num : A) {
            int rem = ((num % B) + B) % B; // handles negative numbers
            count[rem]++;
        }
        long ans = ((long) count[0] * (long) (count[0] - 1)) / 2;
        int i = 1, j = B - 1;
        while (i < j) {
            ans += (long) count[i] * (long) count[j];
            i++;
            j--;
        }
        if (i == j) {
            ans += ((long) count[i] * (long) (count[i] - 1)) / 2;
        }
        return (int) ans % mod;
    }

    //working code
    private static int possiblePairsv1(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> hmap = new HashMap<>();
        int mod = 1000000007;
        for (Integer num : A) {
            int k = num % B;
            hmap.put(k, hmap.getOrDefault(k, 0) + 1);
        }
        long ans = 0L;
        int i = 0, j = B;
        while (i <= j) {
            if (i == 0 && hmap.containsKey(0)) {
                ans += ((long) hmap.get(0) * (long) (hmap.get(0) - 1)) / 2; //remainder 0 possible pairs
            } else if (i == j && hmap.containsKey(i)) {
                ans += ((long) hmap.get(i) * (long) (hmap.get(i) - 1)) / 2;
            } else if (hmap.containsKey(i) && hmap.containsKey(j)) {
                ans += (long) hmap.get(i) * (long) hmap.get(j);
            }
            i++;
            j--;
        }
        return (int) ans % mod;
    }

    //working code but less optimal
    private static int possiblePairsv3(ArrayList<Integer> A, int B) {
        ArrayList<Integer> temp = new ArrayList<>();
        int mod = 1000000007;
        for (int i = 0; i < B; i++) {
            temp.add(0);
        }
        for (Integer num : A) {
            int item;
            if (num < 0) {
                item = num % B + B;
            } else {
                item = num % B;
            }
            temp.set(item, temp.get(item) + 1);
        }
        long ans = ((long) temp.get(0) * (long) (temp.get(0) - 1)) / 2;
        int i = 1, j = B - 1;
        while (i < j) {
            ans += (long) temp.get(i) * (long) temp.get(j);
            i++;
            j--;
        }
        if (i == j) {
            ans += ((long) temp.get(i) * (long) (temp.get(i) - 1)) / 2;
        }
        return (int) ans % mod;
    }

}
