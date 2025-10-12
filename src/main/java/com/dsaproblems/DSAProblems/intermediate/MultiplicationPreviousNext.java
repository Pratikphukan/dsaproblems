package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MultiplicationPreviousNext {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(findMultiplicationPreviousNextv1(input));
        System.out.println(findMultiplicationPreviousNextv2(input));
        System.out.println(findMultiplicationPreviousNextv3(input));
    }

    private static ArrayList<Integer> findMultiplicationPreviousNextv3(ArrayList<Integer> input) {
        int sizeOfList = input.size();
        if (sizeOfList < 2) {
            return new ArrayList<>(List.of(0));
        }
        Supplier<ArrayList<Integer>> getList = ArrayList::new;
        ObjIntConsumer<ArrayList<Integer>> accumulator = ArrayList::add;
        BiConsumer<ArrayList<Integer>, ArrayList<Integer>> addToList = ArrayList::addAll;
        return IntStream.range(0, sizeOfList).map(ele ->
                ele == 0 ? input.get(ele) * input.get(ele + 1) :
                        ele == sizeOfList - 1 ? input.get(sizeOfList - 1) * input.get(sizeOfList - 2) :
                                input.get(ele - 1) * input.get(ele + 1)).collect(getList, accumulator, addToList);
    }

    private static ArrayList<Integer> findMultiplicationPreviousNextv2(ArrayList<Integer> input) {
        ArrayList<Integer> ans = new ArrayList<>();
        int sizeOfList = input.size();
        int value = 0;
        if (sizeOfList < 2) {
            ans.add(value);
            return ans;
        }
        for (int i = 0; i < sizeOfList; i++) {
            value = i == 0 ? input.get(i) * input.get(i + 1) :
                    i == sizeOfList - 1 ? input.get(sizeOfList - 1) * input.get(sizeOfList - 2) :
                            input.get(i - 1) * input.get(i + 1);
            ans.add(value);
        }
        return ans;
    }

    private static ArrayList<Integer> findMultiplicationPreviousNextv1(ArrayList<Integer> input) {
        ArrayList<Integer> ans = new ArrayList<>();
        int sizeOfList = input.size();
        if (sizeOfList < 2) {
            ans.add(0);
            return ans;
        }
        for (int i = 0; i < sizeOfList; i++) {
            if (i == 0) {
                ans.add(input.get(i) * input.get(i + 1));
            } else if (i == input.size() - 1) {
                ans.add(input.get(sizeOfList - 1) * input.get(sizeOfList - 2));
            } else {
                ans.add(input.get(i - 1) * input.get(i + 1));
            }
        }
        return ans;
    }
}
