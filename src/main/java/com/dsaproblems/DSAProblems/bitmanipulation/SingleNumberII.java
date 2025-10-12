package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleNumberII {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, -45, 3, 3, 2, 2, 3, 1, 1));
        System.out.println(findUniqueNumberv1(input));
        System.out.println(findUniqueNumberv2(input));
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
    private static int findUniqueNumberv1(List<Integer> input) {
        int num = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (Integer element : input) {
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

}
