package com.dsaproblems.DSAProblems.advancedJava;

class SharedResource {
    // Private counter variable to be incremented by multiple threads
    private int counter = 0;

    // Synchronized method to increment the counter safely among multiple threads
    synchronized void increment() {
        // Increment the counter by one
        counter++;
    }

    // Synchronized method to get the current value of the counter safely
    synchronized int getCounter() {
        // Return the current counter value
        return counter;
    }
}

class MyIncrementThread extends Thread {
    // Reference to the shared resource object
    private SharedResource sharedResource;

    // Constructor to initialize the shared resource
    MyIncrementThread(SharedResource sharedResource) {
        // Assign the provided shared resource to the instance variable
        this.sharedResource = sharedResource;
    }

    // Run method executed when the thread starts
    public void run() {
        // Loop 1000 times to increment the shared counter
        for (int i = 0; i < 1000; i++) {
            // Call the synchronized increment method on the shared resource
            sharedResource.increment();
        }
    }
}

public class TestMultithreading {

    public static void main(String[] args) {
        // Create a SharedResource instance that will be shared among threads
        SharedResource sharedResource = new SharedResource();

        // Create two thread instances passing the shared resource to each thread
        MyIncrementThread thread1 = new MyIncrementThread(sharedResource);
        MyIncrementThread thread2 = new MyIncrementThread(sharedResource);

        // Start both threads so they begin incrementing the shared counter concurrently
        thread1.start();
        thread2.start();

        // Use try-catch block to handle possible InterruptedException when joining threads
        try {
            // Wait for thread1 to finish execution
            thread1.join();
            // Wait for thread2 to finish execution
            thread2.join();
        } catch (InterruptedException e) {
            // Print any interruption errors that occur
            e.printStackTrace();
        }

        // Print the final value of the counter using the synchronized getCounter method
        // Expected output: "Final Counter : 2000"
        System.out.println("Final Counter : " + sharedResource.getCounter());


        int x = 10;

        Runnable r = () -> System.out.println(x);
        Thread t = new Thread(r);
        t.start();
    }
}
