package com.dsaproblems.DSAProblems.heap01;

import java.util.*;

class PersonBad { // equals overridden, hashCode not overridden -> wrong for HashSet
    String name;
    int age;

    PersonBad(String n, int a) {
        name = n;
        age = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonBad)) return false;
        PersonBad p = (PersonBad) o;
        return age == p.age && Objects.equals(name, p.name);
    }
}

class PersonGood { // both equals and hashCode overridden -> correct
    String name;
    int age;

    PersonGood(String n, int a) {
        name = n;
        age = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonGood)) return false;
        PersonGood p = (PersonGood) o;
        return age == p.age && Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

public class TopKFrequentElements {

    //HashSet uses an element's hashCode() to pick a bucket (fast lookup).
    //If the bucket already contains entries, it calls equals() on those entries to find an exact match.
    //Contract: if a.equals(b) is true then a.hashCode() must equal b.hashCode(). Violating this leads to incorrect behavior (duplicates stored or lookup failures).
    //Good hashCode() distribution improves performance by keeping buckets small.

    public static void main(String[] args) {
        //[1]|1
        //[1, 2, 1, 2, 1, 2, 3, 1, 3, 2]|2
        int[] nums = {1, 2, 1, 2, 1, 2, 3, 1, 3, 2};
        int k = 2;
        System.out.println(findTopKFrequentElementsv1(nums, k));


        Set<PersonBad> badSet = new HashSet<>();
        badSet.add(new PersonBad("Alice", 30));
        badSet.add(new PersonBad("Alice", 30));
        System.out.println("PersonBad set size (wrong): " + badSet.size()); // often 2

        Set<PersonGood> goodSet = new HashSet<>();
        goodSet.add(new PersonGood("Alice", 30));
        goodSet.add(new PersonGood("Alice", 30));
        System.out.println("PersonGood set size (correct): " + goodSet.size()); // 1

    }

    private static int[] findTopKFrequentElementsv1(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 1; //input will atleast have one element
        for (int num : nums) {
            Integer freq = freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            if (freq != null && freq + 1 > maxFreq) maxFreq = freq + 1;
        }
        List<Integer>[] buckets = new List[maxFreq + 1];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int freq = entry.getValue();
            int num = entry.getKey();
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(num);
        }
        int[] result = new int[k];
        int idx = 0;
        for (int freq = maxFreq; freq > 0 && idx < k; freq--) {
            if (buckets[freq] != null) {
                for (int num : buckets[freq]) {
                    result[idx++] = num;
                    if (idx == k) break;
                }
            }
        }
        return result;
    }
}
