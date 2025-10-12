package com.dsaproblems.DSAProblems.advancedJava;

class NewThread implements Runnable {

    Thread thread;

    NewThread() {
        thread = new Thread(this, "pratik-thread");
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + " is running");
    }
}

class TaskB extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running: taskB");
    }
}

class TaskA implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running: taskA");
    }
}

public class SamePriorityThreads {

    public static void main(String[] args) {
        TaskA taskx = new TaskA();
        Thread thread = new Thread(taskx);
        TaskB tasky = new TaskB();

        thread.start();
        tasky.start();

        taskx.run();
        tasky.run();


        Runnable task_1 = () -> {
            System.out.println(Thread.currentThread().getName() + " is running: task-1");
        };

        Runnable task_2 = () -> {
            System.out.println(Thread.currentThread().getName() + " is running: task-2");
        };

        Thread t1 = new Thread(task_1, "task-1-thread");
        Thread t2 = new Thread(task_2, "task-2-thread");

        t1.setPriority(Thread.MAX_PRIORITY);
        t1.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();

        //When two threads of the same priority are scheduled simultaneously,
        // the thread scheduler decides which one runs first. Typically,
        // the operating system uses time-slicing (round-robin) to give each
        // thread a fair share of CPU time. The order of execution is
        // non-deterministic and may vary each run.

        NewThread newThread = new NewThread();
    }
}
