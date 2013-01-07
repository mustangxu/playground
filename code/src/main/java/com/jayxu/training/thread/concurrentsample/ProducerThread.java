/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author ijay
 * 
 */
public class ProducerThread extends Thread {
    private BlockingQueue<Integer> queue;
    private long interval;
    private Random r = new Random();

    public ProducerThread(BlockingQueue<Integer> queue, long interval) {
        super();
        this.queue = queue;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            int n = this.r.nextInt();
            System.err.println("Produced [" + n + "]");

            try {
                this.queue.put(n);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            try {
                sleep(this.interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
