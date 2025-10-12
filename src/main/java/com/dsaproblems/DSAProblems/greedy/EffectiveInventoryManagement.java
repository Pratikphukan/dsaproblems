package com.dsaproblems.DSAProblems.greedy;

import com.dsaproblems.DSAProblems.trie01.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class EffectiveInventoryManagement {

    public static void main(String[] args) {
//		List<Integer> A = new ArrayList<>(Arrays.asList(1, 3, 2, 3, 3));
//		List<Integer> B = new ArrayList<>(Arrays.asList(5, 6, 1, 3, 9));

//		List<Integer> A = new ArrayList<>(Arrays.asList(3, 8, 7, 5));
//		List<Integer> B = new ArrayList<>(Arrays.asList(3, 1, 7, 19));

//        List<Integer> A = new ArrayList<>(Arrays.asList(1, 7, 6, 2, 8, 4, 4, 6, 8, 2));
//        List<Integer> B = new ArrayList<>(Arrays.asList(8, 11, 7, 7, 10, 8, 7, 5, 4, 9));
        //{1=[8], 2=[9, 7], 4=[8, 7], 6=[7, 5], 7=[11], 8=[10, 4]}, 8+9+8+7+7+5+11+10

//		List<Integer> A = new ArrayList<>(Arrays.asList(4, 5, 3));
//		List<Integer> B = new ArrayList<>(Arrays.asList(3, 7, 4));

        List<Integer> A = new ArrayList<>(Arrays.asList(1, 3, 1, 7, 2, 7, 1, 7, 6, 7, 8, 3, 3, 5, 4, 4, 5));
        List<Integer> B = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 4, 4, 4, 11, 8, 11, 8, 7, 7, 7, 6, 10, 4));

        System.out.println(getMaximumProfitv1(A, B));
        System.out.println(getMaximumProfitv2(A, B));
        System.out.println(getMaximumProfitv3(A, B));
        System.out.println(getMaximumProfitv4(A, B));
        System.out.println(getMaximumProfitv5(A, B));
    }

    private static int getMaximumProfitv5(List<Integer> A, List<Integer> B) {
        int n = A.size(), MOD = 1_000_000_007;
        int[][] items = new int[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = A.get(i);
            items[i][1] = B.get(i);
        }
        Arrays.sort(items, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return b[1] - a[1];
        });
        List<Integer> profits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (profits.size() < items[i][0]) {
                profits.add(items[i][1]);
            }
        }
        int sum = 0;
        for (int profit : profits) {
            sum += profit;
        }
        return sum;
    }

    //working code
    private static int getMaximumProfitv4(List<Integer> A, List<Integer> B) {
        int n = A.size(), MOD = 1_000_000_007;
        int[][] items = new int[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = A.get(i);
            items[i][1] = B.get(i);
        }
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            // Add the current item's profit to the heap
            minHeap.offer(items[i][1]);

            // Check if the number of items in the heap exceeds the allowed number of items
            // by the expiration time of the current item.
            // Since the allowed items is equal to expiration time (A[i]), because the last minute you can buy is A[i]-1.
            if (minHeap.size() > items[i][0]) {
                // If so, remove the item with the smallest profit to maximize overall profit.
                minHeap.poll();
            }
        }
        // Sum all the remaining profits in the heap
        long totalProfit = 0;
        while (!minHeap.isEmpty()) {
            // Remove each profit and add it to totalProfit (ensure modulo at each step)
            totalProfit = (totalProfit + minHeap.poll()) % MOD;
        }

        // Return the result as an integer modulo MOD
        return (int) totalProfit;
    }

    private static int getMaximumProfitv3(List<Integer> A, List<Integer> B) {
        Map<Integer, Queue<Integer>> sortedMapAccToKeys = new TreeMap<>(Collections.reverseOrder());
        Queue<Integer> minHeap1 = null;
        for (int i = 0; i < B.size(); i++) {
            if (!sortedMapAccToKeys.containsKey(B.get(i))) {
                minHeap1 = new PriorityQueue<>();
                minHeap1.add(A.get(i));
                sortedMapAccToKeys.put(B.get(i), minHeap1);
            } else {
                minHeap1 = sortedMapAccToKeys.get(B.get(i));
                minHeap1.add(A.get(i));
            }
        }
        int maximumProfit = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();
        int maxProfitForithDay = 0;
        int lastExpiryDate = 0;
        int currentTimestamp = 0;
        for (Map.Entry<Integer, Queue<Integer>> entry : sortedMapAccToKeys.entrySet()) {
            lastExpiryDate = entry.getKey();
            minHeap1 = entry.getValue();
            while (!minHeap1.isEmpty() && currentTimestamp < lastExpiryDate) {
                currentTimestamp += 1;
                maxProfitForithDay = minHeap1.poll();
                minHeap.offer(maxProfitForithDay);
                maximumProfit += maxProfitForithDay;
            }
        }
        currentTimestamp = 1;
        for (Map.Entry<Integer, Queue<Integer>> entry : sortedMapAccToKeys.entrySet()) {
            lastExpiryDate = entry.getKey();
            minHeap1 = entry.getValue();
            while (!minHeap1.isEmpty() && currentTimestamp < lastExpiryDate) {
                if (minHeap.peek() < minHeap1.peek()) {
                    maximumProfit -= minHeap.poll();
                    maximumProfit += minHeap1.peek();
                    minHeap.offer(minHeap1.poll());
                }
                currentTimestamp += 1;
            }
        }
//		while (!maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
//			maximumProfit -= minHeap.poll();
//			maximumProfit += maxHeap.peek();
//			minHeap.offer(maxHeap.poll());
//		}
        return maximumProfit;
    }

    private static int getMaximumProfitv2(List<Integer> A, List<Integer> B) {
        Map<Integer, Queue<Integer>> sortedMapAccToKeys = new TreeMap<>();
        Queue<Integer> maxHeap = null;
        for (int i = 0; i < A.size(); i++) {
            if (!sortedMapAccToKeys.containsKey(A.get(i))) {
                maxHeap = new PriorityQueue<>(Collections.reverseOrder());
                maxHeap.add(B.get(i));
                sortedMapAccToKeys.put(A.get(i), maxHeap);
            } else {
                maxHeap = sortedMapAccToKeys.get(A.get(i));
                maxHeap.add(B.get(i));
            }
        }
        int maximumProfit = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();
        int maxProfitForithDay = 0;
        int lastExpiryDate = 0;
        int currentTimestamp = 0;
        for (Map.Entry<Integer, Queue<Integer>> entry : sortedMapAccToKeys.entrySet()) {
            lastExpiryDate = entry.getKey();
            maxHeap = entry.getValue();
            while (!maxHeap.isEmpty() && currentTimestamp < lastExpiryDate) {
                currentTimestamp += 1;
                maxProfitForithDay = maxHeap.poll();
                minHeap.offer(maxProfitForithDay);
                maximumProfit += maxProfitForithDay;
            }
        }
        currentTimestamp = 1;
        for (Map.Entry<Integer, Queue<Integer>> entry : sortedMapAccToKeys.entrySet()) {
            lastExpiryDate = entry.getKey();
            maxHeap = entry.getValue();
            while (!maxHeap.isEmpty() && currentTimestamp < lastExpiryDate) {
                if (minHeap.peek() < maxHeap.peek()) {
                    maximumProfit -= minHeap.poll();
                    maximumProfit += maxHeap.peek();
                    minHeap.offer(maxHeap.poll());
                }
                currentTimestamp += 1;
            }
        }
//		while (!maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
//			maximumProfit -= minHeap.poll();
//			maximumProfit += maxHeap.peek();
//			minHeap.offer(maxHeap.poll());
//		}
        return maximumProfit;
    }

    private static int getMaximumProfitv1(List<Integer> A, List<Integer> B) {
        Map<Integer, Queue<Integer>> sortedMapAccToKeys = new TreeMap<>();
        Queue<Integer> maxHeap = null;
        for (int i = 0; i < A.size(); i++) {
            if (!sortedMapAccToKeys.containsKey(A.get(i))) {
                maxHeap = new PriorityQueue<>(Collections.reverseOrder());
                maxHeap.add(B.get(i));
                sortedMapAccToKeys.put(A.get(i), maxHeap);
            } else {
                maxHeap = sortedMapAccToKeys.get(A.get(i));
                maxHeap.add(B.get(i));
            }
            for (int j = 0; j < A.size(); j++) {
                if (i != j && A.get(i) <= A.get(j)) {
                    maxHeap = sortedMapAccToKeys.get(A.get(i));
                    maxHeap.add(B.get(j));
                }
            }
        }
        int maximumProfit = 0;
        for (Map.Entry<Integer, Queue<Integer>> entry : sortedMapAccToKeys.entrySet()) {
            maximumProfit += entry.getValue().poll();
        }
        return maximumProfit;
    }

}
