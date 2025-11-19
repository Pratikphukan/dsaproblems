package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleNumberII {

    public static void main(String[] args) {
        //1, 2, -45, 3, 3, 2, 2, 3, 1, 1
        //2, 2, 3, 2
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 2, 3, 2));
        System.out.println(findUniqueNumberv1(input));
        System.out.println(findUniqueNumberv2(input));
        System.out.println(findUniqueNumberv3(input));


        int x1 = 5;      //  00000000 00000000 00000000 00000101
        int c1 = ~x1;    //  11111111 11111111 11111111 11111010 = -6
        System.out.println("x1 = " + x1 + ", ~x1 = " + c1);
        System.out.println(bits(x1) + " -> " + bits(c1));
    }

    private static String bits(int v) {
        return String.format("%32s", Integer.toBinaryString(v)).replace(' ', '0');
    }

    private static int findUniqueNumberv2(List<Integer> input) {
        List<Integer> bits = new ArrayList<>();
        Integer bitCheck = null;
        for (Integer element : input) {
            for (int i = 0; i < 32; i++) {
                bitCheck = ((element >> i) & 1);
                if (bits.size() < 32) {
                    bits.add(0, bitCheck);
                } else {
                    bits.set(31 - i, bits.get(31 - i) + bitCheck);
                    bits.set(31 - i, bits.get(31 - i) % 3);
                }
            }
        }
        int num = 0;
        for (int i = 0; i < 32; i++) {
            if (i == 0) {
                num -= Math.pow(2, 31 - i) * bits.get(i);
            } else {
                num += Math.pow(2, 31 - i) * bits.get(i);
            }
        }
        return num;
    }

    //working code
    //it runs in O(n) time (actually O(32*n) = O(n)) and uses O(1) extra space.
    private static int findUniqueNumberv1(List<Integer> input) {
        int num = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int element : input) {
                if (((element >> i) & 1) == 1) {
                    count++;
                }
            }
            if (count % 3 == 1) {
                num = num | (1 << i);
            }
        }
        return num;
    }

    private static int findUniqueNumberv3(List<Integer> input) {
        // ones: bits that have appeared exactly once (mod 3) so far
        // twos: bits that have appeared exactly twice (mod 3) so far
        int ones = 0, twos = 0;
        for (int x : input) {
            ones = (ones ^ x) & ~twos;
            twos = (twos ^ x) & ~ones;
        }
        return ones;
    }
}
