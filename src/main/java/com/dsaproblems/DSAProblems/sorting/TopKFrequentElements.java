package com.dsaproblems.DSAProblems.sorting;

import java.lang.reflect.Array;
import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(List.of(3, 3, 3, 1, 2, 2, 1, 4));
        int B = 2;
        System.out.println(findTopKFrequentElementsv1(A, B));
        System.out.println(findTopKFrequentElementsv2(A, B));
        System.out.println(findTopKFrequentElementsv3(A, B));
    }

    private static ArrayList<Integer> findTopKFrequentElementsv3(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : A) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(a).equals(frequencyMap.get(b))
                ? Integer.compare(b, a)
                : frequencyMap.get(b) - frequencyMap.get(a));
        for (int num : frequencyMap.keySet()) {
            maxHeap.offer(num);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < B && !maxHeap.isEmpty(); i++) {
            result.add(maxHeap.poll());
        }
        Collections.sort(result);
        return result;
    }

    private static ArrayList<Integer> findTopKFrequentElementsv2(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : A) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(a).equals(frequencyMap.get(b))
                ? Integer.compare(a, b)
                : frequencyMap.get(a) - frequencyMap.get(b));
        for (int num : frequencyMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > B) {
                minHeap.poll(); //removing all the least frequent elements leaving only top B frequent elements
            }
        }
        ArrayList<Integer> result = new ArrayList<>(minHeap);
        Collections.sort(result);
        return result;
    }

    //working code
    private static ArrayList<Integer> findTopKFrequentElementsv1(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : A) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        ArrayList<Integer> uniqueElements = new ArrayList<>(frequencyMap.keySet());
        uniqueElements.sort((a, b) -> frequencyMap.get(a).equals(frequencyMap.get(b))
                ? Integer.compare(b, a)
                : frequencyMap.get(b) - frequencyMap.get(a));
        ArrayList<Integer> topKFrequentElements = uniqueElements.size() > B
                ? new ArrayList<>(uniqueElements.subList(0, B))
                : new ArrayList<>(uniqueElements);
        Collections.sort(topKFrequentElements);
        return topKFrequentElements;
    }
}
