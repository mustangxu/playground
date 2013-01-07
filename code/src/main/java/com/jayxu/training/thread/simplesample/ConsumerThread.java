/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.simplesample;

import java.util.List;

/**
 * @author ijay
 * 
 */
public class ConsumerThread extends Thread {
    private List<Integer> queue;
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
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Consumed [" + this.queue.remove(0)
                        + "]");
                    this.queue.notify();
                    System.out.println("Notified producers");
                }
            }

            if (!this.queue.isEmpty()) {
                try {
                    sleep(this.interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
