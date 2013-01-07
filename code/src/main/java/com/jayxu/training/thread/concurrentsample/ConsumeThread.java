/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.concurrent.BlockingQueue;

/**
 * @author ijay
 * 
 */
public class ConsumeThread extends Thread {
    private BlockingQueue<Integer> queue;
    private long interval;

    public ConsumeThread(BlockingQueue<Integer> queue, long interval) {
        super();
        this.queue = queue;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed [" + this.queue.take() + "]");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("Notified producers");

            try {
                sleep(this.interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
