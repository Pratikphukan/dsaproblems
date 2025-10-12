package com.dsaproblems.DSAProblems.advancedJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        // Create a semaphore with a single permit (initial count = 1)
        Semaphore semaphore = new Semaphore(1);  // Only one thread can acquire the semaphore at a time

        // Create Thread A which will acquire the semaphore first and hold it for some time
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread A: Attempting to acquire the semaphore.");
                    // Thread A acquires the semaphore, blocking if it is not available
                    semaphore.acquire();  // O(1) operation; blocks if the permit is not available
                    System.out.println("Thread A: Semaphore acquired, doing some work.");

                    // Simulate some processing time while holding the semaphore (e.g., 3 seconds)
                    Thread.sleep(3000);  // Simulate work (3000 milliseconds), not CPU-intensive

                } catch (InterruptedException e) {
                    // Handle the interruption exception if the thread is interrupted during sleep or acquire
                    System.out.println("Thread A: Interrupted while waiting.");
                } finally {
                    // Ensure the semaphore is released even if an exception occurs
                    System.out.println("Thread A: Releasing the semaphore.");
                    semaphore.release();  // Release the permit, making it available for Thread B
                }
            }
        });

        // Create Thread B which will try to acquire the semaphore after Thread A
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Small delay to ensure Thread A attempts first (simulate ordering)
                    Thread.sleep(500);  // This delay ensures that Thread A acquires the semaphore before Thread B attempts

                    System.out.println("Thread B: Attempting to acquire the semaphore.");
                    // Thread B attempts to acquire the semaphore; it will block until Thread A releases it
                    semaphore.acquire();  // O(1) operation; blocks until a permit is available

                    System.out.println("Thread B: Successfully acquired the semaphore.");

                } catch (InterruptedException e) {
                    // Handle the interruption exception if the thread is interrupted while waiting
                    System.out.println("Thread B: Interrupted while waiting.");
                } finally {
                    // Always release the semaphore if it was acquired
                    System.out.println("Thread B: Releasing the semaphore.");
                    semaphore.release();  // Release the permit after finishing work
                }
            }
        });

        // Start both threads to demonstrate semaphore behavior
        threadA.start();  // Start Thread A; acquires semaphore first
        threadB.start();  // Start Thread B; will block until Thread A releases the semaphore

        // Join threads to the main thread to ensure the application waits for them to finish
        try {
            threadA.join();  // Wait for Thread A to finish execution
            threadB.join();  // Wait for Thread B to finish execution
        } catch (InterruptedException e) {
            // Handle any interruptions during the join operations
            System.out.println("Main thread interrupted while waiting for threads to finish.");
        }

        // Final message to indicate the demonstration is complete
        System.out.println("Semaphore demonstration finished.");


        List<String> list = new ArrayList<>(List.of("apple", "mango", "guava"));

        // Define a Comparator to sort strings in descending order.
        // For each pair of strings (s1, s2), we compare them in reverse order using s2.compareTo(s1)
        Comparator<String> cmp = (s1, s2) -> s2.compareTo(s1);

        // Sort the list using Collections.sort() and the defined comparator.
        // Time Complexity: O(n * log n) on average due to the sorting algorithm.
        Collections.sort(list, cmp);

        // Print the sorted list.
        System.out.println(list); // Expected output: [mango, guava, apple]
    }
}
