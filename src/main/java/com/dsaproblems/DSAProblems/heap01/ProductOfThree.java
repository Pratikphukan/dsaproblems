package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ProductOfThree {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(11, 5, 6, 2, 8, 10));
        System.out.println(findProductOf3v1(A));
        System.out.println(findProductOf3v2(A));
        System.out.println(findProductOf3v3(A));
    }

    //working code
    private static ArrayList<Integer> findProductOf3v3(List<Integer> input) {
        ArrayList<Integer> maxProducts = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < input.size(); i++) {
            minHeap.offer(input.get(i));
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
            if (i < 2) {
                maxProducts.add(-1);
            } else {
                // Multiply all elements in the heap (size is 3)
                int prod = 1;
                for (int num : minHeap) {
                    prod *= num;
                }
                maxProducts.add(prod);
            }
        }
        return maxProducts;
    }

    // working code
    //for each iteration, it does 3 polls and 3 inserts: (O(6*log(n))) per iteration,
    // leading to O(n*log(n)) overall
    private static ArrayList<Integer> findProductOf3v2(List<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < A.size(); i++) {
            maxheap.add(A.get(i));
            if (i < 2) {
                ans.add(-1);
            } else {
                int a = maxheap.poll(), b = maxheap.poll(), c = maxheap.poll();
                ans.add(a * b * c);
                maxheap.add(a);
                maxheap.add(b);
                maxheap.add(c);
            }
        }
        return ans;
    }

    //working code
    private static List<Integer> findProductOf3v1(List<Integer> input) {
        List<Integer> maxProducts = new ArrayList<>(Arrays.asList(-1, -1));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int prod = 1;
        for (int i = 0; i < 3; i++) {
            prod *= input.get(i);
            minHeap.add(input.get(i));
        }
        maxProducts.add(prod);
        for (int i = 3; i < input.size(); i++) { // size of the minHeap will be fixed
            if (!minHeap.isEmpty() && minHeap.peek() < input.get(i)) {
                int minElementTillLen = minHeap.poll();
                prod /= minElementTillLen;
                prod *= input.get(i);
                minHeap.add(input.get(i));
            }
            maxProducts.add(prod);
        }
        return maxProducts;
    }

}
