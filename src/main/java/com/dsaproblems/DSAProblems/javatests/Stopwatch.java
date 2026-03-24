package com.dsaproblems.DSAProblems.javatests;

public class Stopwatch {

    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public Stopwatch() {
        this.startTime = 0;
        this.stopTime = 0;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            this.startTime = System.currentTimeMillis();
            this.isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            this.stopTime = System.currentTimeMillis();
            this.isRunning = false;
        }
    }

    public long getElapsedTime() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return stopTime - startTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Stopwatch sw = new Stopwatch();
        System.out.println(sw.getElapsedTime());
        sw.start();
        Thread.sleep(1000);
        System.out.println(sw.getElapsedTime());
    }
}
