/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ProducerThread extends Thread {
    private BlockingQueue<Integer> queue;
    private long interval;
    private Random r = new Random();

    public ProducerThread(BlockingQueue<Integer> queue, long interval) {
        this.queue = queue;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            var n = this.r.nextInt();
            System.err.println("Produced [" + n + "]");

            try {
                this.queue.put(n);
            } catch (InterruptedException e1) {
                Thread.currentThread().interrupt();
            }

            try {
                Thread.sleep(this.interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
