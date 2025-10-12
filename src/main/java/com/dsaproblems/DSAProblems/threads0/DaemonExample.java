package com.dsaproblems.DSAProblems.threads0;

//The setDaemon(boolean) method in Java's Thread class marks a thread as a daemon
// thread. Daemon threads run in the background and do not prevent the JVM from
// exiting when all user (non-daemon) threads finish. Use setDaemon(true) before
// starting the thread to make it a daemon.
public class DaemonExample {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        daemonThread.setDaemon(true); // Mark as daemon
        daemonThread.start();

        System.out.println("Main thread finished.");
    }
}
