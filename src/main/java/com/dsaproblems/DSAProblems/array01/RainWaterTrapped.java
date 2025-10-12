package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.List;

public class RainWaterTrapped {

    public static void main(String[] args) {
        // (1,7,3,4,9,9),(1,3,4,6,2,8,6,5,10,12,14,16,9)
        // (0,2,4,0,2,1,2,6),(1,2,1,2)
        ArrayList<Integer> A = new ArrayList<>(List.of(0, 2, 4, 0, 2, 1, 2, 6));

        System.out.println(maxWaterTrappedv1(A));
        System.out.println(maxWaterTrappedv2(A));
        System.out.println(maxWaterTrappedv3(A));
    }

    private static int maxWaterTrappedv2(ArrayList<Integer> A) {
        int n = A.size();
        int left = 0;
        int right = n - 1;
        int res = 0;
        int maxleft = 0, maxright = 0;
        while (left <= right) {
            // When height of left side is lower, calculate height of water trapped in left
            // bin else calculate for right bin
            if (A.get(left) <= A.get(right)) {
                if (A.get(left) >= maxleft)
                    maxleft = A.get(left); // This index wont store any water as there is no index towards the left
                    // whose
                    // height is greater than this.
                else
                    res += maxleft - A.get(left);
                left++;
            } else {
                if (A.get(right) >= maxright)
                    maxright = A.get(right); // This index wont store any water as there is no index towards the right
                    // whose
                    // height is greater than this.
                else
                    res += maxright - A.get(right);
                right--;
            }
        }
        return res;
    }

    //working code
    private static int maxWaterTrappedv3(ArrayList<Integer> A) {
        int[] LME = lme(A);//get left max for i->[0,1], including current element
        int[] RME = rme(A);//get right max fot i->[i, n], including current element
        int n = A.size(), sum = 0;
        for (int i = 1; i < n - 1; i++) {
            //For each bar (excluding the first and last), the water trapped is the minimum of left
            // and right max heights minus the bar's own height
            int h = Math.min(LME[i - 1], RME[i + 1]);
            sum += Math.max(h - A.get(i), 0);
        }
        return sum;
    }

    private static int[] lme(ArrayList<Integer> A) {
        int n = A.size();
        int[] lme = new int[n];
        lme[0] = A.get(0);
        for (int i = 1; i < n; i++) {
            lme[i] = Math.max(A.get(i), lme[i - 1]);
        }
        return lme;
    }

    private static int[] rme(ArrayList<Integer> A) {
        int n = A.size();
        int[] rme = new int[n];
        rme[n - 1] = A.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            rme[i] = Math.max(A.get(i), rme[i + 1]);
        }
        return rme;
    }

    private static int maxWaterTrappedv1(ArrayList<Integer> A) {
        int n = A.size();
        List<Integer> lmax = leftMax(A);
        List<Integer> rmax = rightMax(A);
        int sum = 0;
        for (int i = 1; i < n - 1; i++) {
            int h = Math.min(lmax.get(i - 1), rmax.get(i + 1));
            sum += Math.max(h - A.get(i), 0);
        }
        return sum;
    }

    private static List<Integer> rightMax(List<Integer> A) {
        int n = A.size();
        List<Integer> rightmax = new ArrayList<>(n); //Pre-sizing a list means initializing it with a specific capacity before adding elements,  it allocates enough memory for n elements up front
        for (int i = 0; i < n; i++) rightmax.add(0);
        rightmax.set(n - 1, A.get(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            rightmax.set(i, Math.max(A.get(i), rightmax.get(i + 1)));
        }
        return rightmax;
    }

    private static List<Integer> leftMax(List<Integer> A) {
        int n = A.size();
        List<Integer> leftmax = new ArrayList<>(n);
        leftmax.add(A.get(0));
        for (int i = 1; i < n; i++) {
            leftmax.add(Math.max(A.get(i), leftmax.get(i - 1)));
        }
        return leftmax;
    }
}
