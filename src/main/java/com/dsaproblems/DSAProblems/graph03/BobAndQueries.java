package com.dsaproblems.DSAProblems.graph03;

import java.util.ArrayList;
import java.util.Arrays;

public class BobAndQueries {

    static class BIT {
        int n;
        int[] tree;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int i, int delta) {
            while (i <= n) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }

        int range(int l, int r) {
            return query(r) - query(l - 1);
        }
    }

    public static ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        int n = A;

        long[] val = new long[n + 1];
        BIT bit = new BIT(n);

        ArrayList<Integer> res = new ArrayList<>();

        for (ArrayList<Integer> q : B) {
            int type = q.get(0);
            int x = q.get(1);
            int y = q.get(2);

            if (type == 1) {
                int old = Long.bitCount(val[x]);
                val[x] = val[x] * 2 + 1;
                int now = Long.bitCount(val[x]);
                bit.update(x, now - old);

            } else if (type == 2) {
                int old = Long.bitCount(val[x]);
                val[x] = val[x] / 2;
                int now = Long.bitCount(val[x]);
                bit.update(x, now - old);

            } else {
                res.add(bit.range(x, y));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int A = 5;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 1, -1)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2, -1)));
        edges.add(new ArrayList<>(Arrays.asList(1, 3, -1)));
        edges.add(new ArrayList<>(Arrays.asList(3, 1, 3)));
        edges.add(new ArrayList<>(Arrays.asList(3, 2, 4)));
        System.out.println(solve(A, edges));
    }
}
