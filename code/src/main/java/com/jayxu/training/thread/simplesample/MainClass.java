/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.simplesample;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ijay
 */
public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < 10; i++) {
            queue.add(i + 1);
            System.out.println(i);
        }

//        for (Integer i : queue) {
//            System.out.println(i);
//        }

        ProducerThread producer = new ProducerThread(queue, 10, 5000);
        ConsumerThread consumer = new ConsumerThread(queue, 500);

        producer.start();
        Thread.sleep(1000L);
        consumer.start();
    }
}
