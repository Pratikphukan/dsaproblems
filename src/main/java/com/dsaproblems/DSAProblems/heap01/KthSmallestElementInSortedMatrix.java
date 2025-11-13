package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElementInSortedMatrix {

    static class Node {
        int val, r, c;

        Node(int v, int r, int c) {
            this.val = v;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(5, 9, 11));
        input.add(Arrays.asList(9, 11, 13));
        input.add(Arrays.asList(10, 12, 15));
        input.add(Arrays.asList(13, 14, 16));
        input.add(Arrays.asList(16, 20, 21));

        int B = 12;

        System.out.println(findBthSmallestElementInMatrixv1(input, B));
        System.out.println(findBthSmallestElementInMatrixv2(input, B));
        System.out.println(findBthSmallestElementInMatrixv3(input, B));
        System.out.println(findBthSmallestElementInMatrixv4(input, B));
    }

    //working code
    private static int findBthSmallestElementInMatrixv4(List<List<Integer>> matrix, int B) {
        int n = matrix.size(), m = matrix.get(0).size();
        int low = matrix.get(0).get(0), high = matrix.get(n - 1).get(m - 1);
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countLessEqual(matrix, mid);
            if (count < B) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static int countLessEqual(List<List<Integer>> matrix, int mid) {
        int n = matrix.size(), m = matrix.get(0).size();
        int row = n - 1, col = 0;
        int count = 0;
        while (row >= 0 && col < m) {
            if (matrix.get(row).get(col) <= mid) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }
        return count;
    }

    //working code
    //java.util.PriorityQueue does not allow null elements.
    // Adding null (e.g., add(null) or offer(null)) throws NullPointerException.
    // peek()/poll() may return null to signal the queue is empty, but storing
    // null is prohibited. The same restriction applies to most Java queue
    // implementations that rely on comparisons
    private static int findBthSmallestElementInMatrixv3(List<List<Integer>> input, int B) {
        int rows = input.size();
        Queue<Node> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (int r = 0; r < rows && r < B; r++) {
            minHeap.offer(new Node(input.get(r).get(0), r, 0));
        }
        Node node = null;
        for (int i = 0; i < B; i++) {
            if (!minHeap.isEmpty()) {
                node = minHeap.poll();
                int r = node.r, c = node.c;
                if (c + 1 < input.get(r).size()) {
                    minHeap.offer(new Node(input.get(r).get(c + 1), r, c + 1));
                }
            }
        }
        return node != null ? node.val : -1;
    }

    //working code
    private static Integer findBthSmallestElementInMatrixv2(List<List<Integer>> input, int B) {
        int cols = input.get(0).size();
        Queue<Integer> maxHeap = new PriorityQueue<>(new IntegerCompare());
        for (List<Integer> row : input) {
            for (int j = 0; j < cols; j++) {
                if (maxHeap.size() < B) {
                    maxHeap.add(row.get(j));
                } else {
                    if (row.get(j) < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.offer(row.get(j));
                    }
                }
            }
        }
        return maxHeap.peek();
    }

    private static Integer findBthSmallestElementInMatrixv1(List<List<Integer>> input, int B) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (List<Integer> row : input) {
            for (Integer element : row) {
                if (maxHeap.size() < B) {
                    maxHeap.add(element);
                } else {
                    if (maxHeap.peek() > element) {
                        maxHeap.poll();
                        maxHeap.add(element);
                    }
                }
            }
        }
        return maxHeap.peek();
    }

}
