/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ijay
 * 
 */
public class MainClass {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);

        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }

        ProducerThread producer = new ProducerThread(queue, 6000);
        ConsumeThread consumer = new ConsumeThread(queue, 500);

        producer.start();
        consumer.start();
    }
}
