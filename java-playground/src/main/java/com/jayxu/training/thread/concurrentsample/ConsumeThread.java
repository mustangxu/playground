/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.concurrent.BlockingQueue;

public class ConsumeThread extends Thread {
    private BlockingQueue<Integer> queue;
    private long interval;

    public ConsumeThread(BlockingQueue<Integer> queue, long interval) {
        this.queue = queue;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed [" + this.queue.take() + "]");
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Notified producers");

            try {
                Thread.sleep(this.interval);
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
