package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class BthSmallestPrimeFraction {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 5));
        int B = 3;
        System.out.println(findBthSmallestFractionv1(input, B));
        System.out.println(findBthSmallestFractionv2(input, B));
        System.out.println(findBthSmallestFractionv3(input, B));
    }

    private static ArrayList<Integer> findBthSmallestFractionv3(List<Integer> input, int B) {
        int K = B;
        PriorityQueue<Fraction> q = new PriorityQueue<>();
        Map<Integer, Integer> nxt = new HashMap<>();
        for (int i = input.size() - 1; i > 0; i--) {
            if (nxt.containsKey(input.get(i)))
                nxt.replace(input.get(i), input.get(i - 1));
            else
                nxt.put(input.get(i), input.get(i - 1));
        }
        double last = (double) input.get(input.size() - 1);
        double d;
        if (nxt.containsKey(1) == true)
            nxt.replace(1, 0);
        else
            nxt.put(1, 0);
        for (int i = 0; i < input.size(); i++) {
            d = (double) input.get(i) / last;
            q.offer(new Fraction(input.get(i), (int) last, d));
        }
        while (q.size() > 0 && K > 0) {
            K--;
            if (K == 0)
                break;
            Fraction p = q.poll();
            if (nxt.containsKey(p.getNumerator()) == true && nxt.get(p.getDenominator()) != 0) {
                p.setDenominator(nxt.get(p.getDenominator()));
                d = (double) p.getNumerator() / (double) p.getDenominator();
                q.offer(new Fraction(p.getNumerator(), p.getDenominator(), d));
            }
        }
        return new ArrayList<Integer>(Arrays.asList(q.peek().getNumerator(), q.peek().getDenominator()));

    }

    private static List<Integer> findBthSmallestFractionv2(List<Integer> input, int B) {
        Queue<Fraction> min_heap = new PriorityQueue<>();
        int len = input.size();
        List<Integer> ans = new ArrayList<>(Arrays.asList(0, 0));
        for (int i = 0; i < len; i++) { // no need for a(2)/a(1) > 0
            for (int j = i + 1; j < len; j++) {
                min_heap.offer(new Fraction(input.get(i), input.get(j), input.get(i) * 1d / input.get(j)));
            }
        }
        for (int i = 0; i < B; i++) {
            Fraction currMin = min_heap.poll();
            if (i == B - 1) {
                ans.set(0, currMin.getNumerator());
                ans.set(1, currMin.getDenominator());
            }
        }
        return ans;
    }

    private static double findBthSmallestFractionv1(List<Integer> input, int B) {
        Queue<Double> min_heap = new PriorityQueue<>(new DoubleCompare());
        int len = input.size();
        for (int i = 0; i < len; i++) { // no need for a(2)/a(1) > 0
            for (int j = i + 1; j < len; j++) {
                min_heap.offer(input.get(i) * 1d / input.get(j));
            }
        }
        double ans = 0d;
        for (int i = 0; i < B; i++) {
            ans = min_heap.poll();
        }
        return ans;
    }

}
