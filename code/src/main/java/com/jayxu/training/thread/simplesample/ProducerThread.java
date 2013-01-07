/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.simplesample;

import java.util.List;
import java.util.Random;

/**
 * @author ijay
 * 
 */
public class ProducerThread extends Thread {
    private List<Integer> queue;
    private long interval;
    private int capacity;
    private Random r = new Random();

    public ProducerThread(List<Integer> queue, int capacity, long interval) {
        super();
        this.queue = queue;
        this.interval = interval;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.queue) {
                System.err.println("Enter producer's synchronized block");

                if (this.queue.size() >= this.capacity) {
                    System.err.println("Queue is full");

                    try {
                        System.err.println("Producer is waiting ...");
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    int n = this.r.nextInt();
                    System.err.println("Produced [" + n + "]");
                    this.queue.add(n);

                    this.queue.notify();
                    System.err.println("Notified consumers");
                }
            }

            if (this.queue.size() < this.capacity) {
                try {
                    sleep(this.interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
