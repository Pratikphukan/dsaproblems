package com.dsaproblems.DSAProblems.advancedJava;

// synchronized blocks can be more efficient than synchronizing the entire
// method because they allow you to limit synchronization to only the
// critical section of code that needs thread safety, reducing contention
// and improving performance
class Counter {
    int count = 0;

    public void increment() {

        // Only the increment operation is synchronized
        // so threads can execute non-critical code without waiting for the lock,
        // making it more efficient than synchronizing the entire method.
        synchronized (this) {
            count++;
        }
        // Other non-critical code can run without locking
    }
}

class TaskC implements Runnable {

    BlockedStateExample example;

    TaskC(BlockedStateExample example) {
        this.example = new BlockedStateExample();
    }

    @Override
    public void run() {
        example.syncMethod();
    }
}

public class BlockedStateExample {

    //The BLOCKED state in a thread's lifecycle indicates that the thread is
    // waiting to acquire a monitor lock to enter a synchronized block or
    // method. It cannot proceed until another thread releases the lock.
    //Thread-1 enters syncMethod and holds the lock.
    //Thread-2 tries to enter but is BLOCKED until Thread-1 finishes.
    public synchronized void syncMethod() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        //using a synchronized block or method is a good option when a
        // shared object is being modified by multiple threads.
        // It ensures that only one thread can access the critical section
        // at a time, preventing race conditions and maintaining data consistency
        BlockedStateExample example = new BlockedStateExample();
        TaskC task = new TaskC(example);

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thraed-2");

        t1.start();
        t2.start();
    }

}
