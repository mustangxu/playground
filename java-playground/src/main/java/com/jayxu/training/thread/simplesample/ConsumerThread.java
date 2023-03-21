/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.simplesample;

import java.util.List;

public class ConsumerThread extends Thread {
    private final List<Integer> queue;
    private long interval;

    public ConsumerThread(List<Integer> queue, long interval) {
        super("Consumer");
        this.queue = queue;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.queue) {
                System.out.println("Enter consumer's synchronized block");

                if (this.queue.isEmpty()) {
                    System.out.println("Queue is empty");

                    try {
                        System.out.println("Consumer is waiting ...");
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out
                        .println("Consumed [" + this.queue.remove(0) + "]");
                    this.queue.notifyAll();
                    System.out.println("Notified producers");
                }

                if (!this.queue.isEmpty()) {
                    try {
                        Thread.sleep(this.interval);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
