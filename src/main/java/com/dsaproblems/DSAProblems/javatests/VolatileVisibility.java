package com.dsaproblems.DSAProblems.javatests;

import java.util.concurrent.ConcurrentHashMap;

public class VolatileVisibility {

    private static boolean flag = false;

    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        concurrentHashMap.put("A", 1);
        int valueA = concurrentHashMap.get("A");
        System.out.println(valueA);
        int valueB = concurrentHashMap.compute("B", (key, oldValue) -> (oldValue == null) ? 1 : oldValue + 1);
        System.out.println(valueB);
        int valueC = concurrentHashMap.merge("C", 1, Integer::sum);
        concurrentHashMap.remove("A");
        boolean containsKeyB = concurrentHashMap.containsKey("B");
        System.out.println(containsKeyB);

        new Thread(() -> {
            System.out.println("Waiting for flag to become true...");
            while (!flag) {
                //busy waiting
            }
            System.out.println("Flag is now true.");
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flag = true;
    }
}
