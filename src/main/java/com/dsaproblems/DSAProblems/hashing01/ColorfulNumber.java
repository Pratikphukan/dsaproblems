package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColorfulNumber {

    public static void main(String[] args) {
        System.out.println(checkColorfulNumber(3245));
        System.out.println(checkColorfulNumberv1(3245));
        System.out.println(checkColorfulNumberv2(3245));
    }

    private static int checkColorfulNumberv2(int num) {
        Set<Integer> set = new HashSet<>();
        List<Integer> digits = new ArrayList<>();
        Integer temp = num;
        Integer digit = null;
        while (temp > 0) {
            digit = temp % 10;
            if (set.contains(digit)) {
                return 0;
            }
            set.add(digit);
            digits.add(0, digit);
            temp = temp / 10;
        }
        for (int i = 0; i < digits.size(); i++) {
            Integer prod = digits.get(i);
            for (int j = i + 1; j < digits.size(); j++) {
                prod = prod * digits.get(j);
                if (set.contains(prod)) {
                    return 0;
                }
                set.add(prod);
            }
        }
        return 1;
    }

    private static int checkColorfulNumberv1(int num) {
        Set<Integer> set = new HashSet<>();
        Integer digit = null;
        while (num > 0) {
            Integer prod = 1;
            Integer temp = num;
            while (temp > 0) {
                digit = temp % 10;
                prod = prod * digit;
                if (!set.contains(prod)) {
                    set.add(prod);
                } else {
                    return 0;
                }
                temp = temp / 10;
            }
            num = num / 10;
        }
        return 1;
    }

    private static int checkColorfulNumber(int num) {
        Set<Integer> set = new HashSet<>();
        Integer digit = null;
        Integer productFromLast = 1;
        while (num > 0) {
            digit = num % 10;
            num = num / 10;
            if (set.contains(digit))
                return 0;
            set.add(digit);
            productFromLast = productFromLast * digit;
        }
        return 1;
    }

}
