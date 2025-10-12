package com.dsaproblems.DSAProblems.queue01;

import java.util.*;

import com.dsaproblems.DSAProblems.heap01.IntegerCompare;

public class SumOfMinMax {

    public static void main(String[] args) {
        // 2, 5, -1, 7, -3, -1, -2 | 4
        // 6, 5, 4, 3, 2, 1
        // 1, 2, 3, 4, 5, 6 | 4
        // 2,-1,3 | 2
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int window = 4;
        System.out.println(findSumOfMinMaxInWindowv1(input, window));
        System.out.println(findSumOfMinMaxInWindowv2(input, window));
        System.out.println(findSumOfMinMaxInWindowv3(input, window));
        System.out.println(findSumOfMinMaxInWindowv4(input, window));
        System.out.println(findSumOfMinMaxInWindowv5(input, window));


        ArrayList<Integer> input1 = new ArrayList<>(List.of(2, 5, -1, 7, -3, -1, -2));
        int B = 4;
        System.out.println(findKthLargest1x(input1, B));
    }

    //not working
    private static Integer findKthLargest1x(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < B; i++) {
            minHeap.add(A.get(i));
            maxHeap.add(A.get(i));
        }
        long sum = minHeap.peek() + maxHeap.peek();
        for (int i = B; i < A.size(); i++) {
            if (minHeap.peek() == A.get(i - B)) {
                minHeap.poll();
                minHeap.add(A.get(i));
            }
            if (A.get(i) < minHeap.peek()) {
                minHeap.add(A.get(i));
            }
            if (maxHeap.peek() == A.get(i - B)) {
                maxHeap.poll();
                maxHeap.add(A.get(i));
            }
            if (A.get(i) > maxHeap.peek()) {
                maxHeap.add(A.get(i));
            }
            sum += minHeap.peek() + maxHeap.peek();
        }
        return (int) sum % 1_000_000_000;
    }

    private static int findSumOfMinMaxInWindowv5(List<Integer> input, int window) {
        int mod = 1000000007;
        Deque<Integer> smallerStack = new LinkedList<>();
        Deque<Integer> largerStack = new LinkedList<>();
        int sum = 0;
        int i = 1;
        int len = input.size();
        largerStack.offerFirst(0);
        smallerStack.offerFirst(0);
        for (; i < window; i++) {
            while (!largerStack.isEmpty() && input.get(largerStack.peekFirst()) < input.get(i)) {
                largerStack.pollFirst();
            }
            if (largerStack.isEmpty() || input.get(largerStack.peekFirst()) >= input.get(i))
                largerStack.offerFirst(i);
            while (!smallerStack.isEmpty() && input.get(smallerStack.peekFirst()) > input.get(i)) {
                smallerStack.pollFirst();
            }
            if (smallerStack.isEmpty() || input.get(smallerStack.peekFirst()) <= input.get(i))
                smallerStack.offerFirst(i);
        }
        int value;
        for (; i < len; i++) {
            value = (input.get(largerStack.peekLast()) % mod + input.get(smallerStack.peekLast()) % mod) % mod;
            sum += value;
            sum %= mod;
            while (!smallerStack.isEmpty() && smallerStack.peekLast() <= i - window)
                smallerStack.pollLast();
            while (!largerStack.isEmpty() && largerStack.peekLast() <= i - window)
                largerStack.pollLast();
            while (!largerStack.isEmpty() && input.get(largerStack.peekFirst()) < input.get(i)) {
                largerStack.pollFirst();
            }
            if (largerStack.isEmpty() || input.get(largerStack.peekFirst()) >= input.get(i))
                largerStack.offerFirst(i);
            while (!smallerStack.isEmpty() && input.get(smallerStack.peekFirst()) > input.get(i)) {
                smallerStack.pollFirst();
            }
            if (smallerStack.isEmpty() || input.get(smallerStack.peekFirst()) <= input.get(i))
                smallerStack.offerFirst(i);
        }
        sum += input.get(largerStack.peekLast()) + input.get(smallerStack.peekLast());
        sum %= mod;
        return sum % mod;
    }

    private static int findSumOfMinMaxInWindowv4(List<Integer> input, int window) {
        Deque<Integer> smallerStack = new LinkedList<>();
        Deque<Integer> largerStack = new LinkedList<>();
        int sum = 0;
        int i = 0;
        int len = input.size();
        for (; i < window; i++) {
            while (!largerStack.isEmpty() && input.get(largerStack.peekFirst()) < input.get(i)) {
                largerStack.pollFirst();
            }
            while (!smallerStack.isEmpty() && input.get(smallerStack.peekFirst()) > input.get(i)) {
                smallerStack.pollFirst();
            }
            largerStack.offerFirst(i);
            smallerStack.offerFirst(i);
        }
        for (; i < len; i++) {
            sum += input.get(largerStack.peekLast()) + input.get(smallerStack.peekLast());
            while (!smallerStack.isEmpty() && smallerStack.peekLast() <= i - window)
                smallerStack.pollLast();
            while (!largerStack.isEmpty() && largerStack.peekLast() <= i - window)
                largerStack.pollLast();
            while (!largerStack.isEmpty() && input.get(largerStack.peekFirst()) < input.get(i)) {
                largerStack.pollFirst();
            }
            while (!smallerStack.isEmpty() && input.get(smallerStack.peekFirst()) > input.get(i)) {
                smallerStack.pollFirst();
            }
            largerStack.offerFirst(i);
            smallerStack.offerFirst(i);
        }
        sum += input.get(largerStack.peekLast()) + input.get(smallerStack.peekLast());
        return sum;
    }

    private static int findSumOfMinMaxInWindowv3(List<Integer> input, int window) {
        Deque<Integer> S = new LinkedList<>();
        Deque<Integer> G = new LinkedList<>();
        int sum = 0;
        int i = 0;
        int len = input.size();
        for (; i < window; i++) {
            while (!S.isEmpty() && input.get(S.peekLast()) >= input.get(i)) {
                S.pollLast();
            }
            while (!G.isEmpty() && input.get(G.peekLast()) <= input.get(i)) {
                G.pollLast();
            }
            S.offerLast(i);
            G.offerLast(i);
        }
        for (; i < len; i++) {
            sum += input.get(S.peekFirst()) + input.get(G.peekFirst());
            while (!S.isEmpty() && S.peekFirst() <= i - window)
                S.removeFirst();
            while (!G.isEmpty() && G.peekFirst() <= i - window)
                G.removeFirst();
            while (!S.isEmpty() && input.get(S.peekLast()) >= input.get(i)) {
                S.pollLast();
            }
            while (!G.isEmpty() && input.get(G.peekLast()) <= input.get(i)) {
                G.pollLast();
            }
            S.offerLast(i);
            G.offerLast(i);
        }
        sum += input.get(S.peekFirst()) + input.get(G.peekFirst());
        return sum;
    }

    private static int findSumOfMinMaxInWindowv2(List<Integer> input, int window) {
        int MOD = 1000000007;
        int len = input.size();
        int maxi, mini, item;
        int windowLen;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            windowLen = 0;
            for (int j = i; j < len; j++) {
                windowLen++;
                if (windowLen == window) {
                    maxi = Integer.MIN_VALUE;
                    mini = Integer.MAX_VALUE;
                    for (int idx = i; idx <= j; idx++) {
                        maxi = Math.max(maxi, input.get(idx));
                        mini = Math.min(mini, input.get(idx));
                    }
                    item = (maxi % MOD + mini % MOD) % MOD;
                    sum = (sum % MOD + item % MOD) % MOD;
                }
            }
        }
        return sum;
    }

    private static int findSumOfMinMaxInWindowv1(List<Integer> input, int window) {
        Queue<Integer> max_heap = new PriorityQueue<>(new IntegerCompare());
        Queue<Integer> min_heap = new PriorityQueue<>();
        int MOD = 1000000007;
        int ans = 0;
        int len = input.size();
        Integer item = null;
        for (int i = 0; i < len; i++) {
            min_heap.add(input.get(i));
            max_heap.add(input.get(i));
            if (i - window + 1 >= 0) {
                item = (min_heap.peek() % MOD + max_heap.peek() % MOD) % MOD;
                ans = (ans % MOD + item % MOD) % MOD;
                min_heap.remove(input.get(i - window + 1));
                max_heap.remove(input.get(i - window + 1));
            }
        }
        return ans % MOD;
    }

}
