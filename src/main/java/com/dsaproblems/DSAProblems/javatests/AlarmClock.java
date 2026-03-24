package com.dsaproblems.DSAProblems.javatests;

public class AlarmClock {

    private int hour;
    private int minute;
    private boolean alarmSet;

    public AlarmClock(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        alarmSet = false;
    }

    public void setAlarm(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.alarmSet = true;
    }

    public void disableAlarm() {
        this.alarmSet = false;
    }

    public String ring() {
        if (alarmSet) {
            return "Wake up! It's time!";
        } else {
            return "No alarm set.";
        }
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println("MyThread is running");
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getId() + " Value " + i);
        }
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("MyRunnable is running");
    }
}

class Check {
    public static void main(String[] args) {
        AlarmClock alarmClock = new AlarmClock(2, 2);

        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();

        Thread thread3 = new Thread(new MyRunnable());
        thread3.start();
    }
}

