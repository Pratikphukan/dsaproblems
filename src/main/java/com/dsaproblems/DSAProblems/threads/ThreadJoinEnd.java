package com.dsaproblems.DSAProblems.threads;

public class ThreadJoinEnd {

    public static void main(String[] args) {
        int[] marks = new int[10];
        Thread marksThread = new Thread(new Marks(marks));
        Thread avgThread = new Thread(new Average(marks));
        marksThread.start();
        try {
            marksThread.join(); //lets marksThread finish before starting avgThread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        avgThread.start();
    }
}
