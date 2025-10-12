package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class FractionalKnapsack {

    static class Item {
        int value, weight;
        double ratio;

        public Item(int v, int w) {
            this.value = v;
            this.weight = w;
            ratio = v / (double) w;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(60, 100, 120));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(10, 20, 30));
        int C = 50; // expect 240.0 -> 24000
        System.out.println(maxValuev1(A, B, C));
    }

    private static int maxValuev1(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int n = A.size();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(A.get(i), B.get(i));
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        double total = 0.0;
        int cap = C;

        for (Item item : items) {
            if (cap == 0) break;
            if (item.weight <= cap) {
                cap -= item.weight;
                total += item.value;
            } else {
                total += item.ratio * cap;
                cap = 0;
            }
        }
        // avoid precision issues when flooring
        return (int) Math.floor(total * 100.0 + 1e-9);
    }
}
