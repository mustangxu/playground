/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.simplesample;

import java.util.LinkedList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) throws InterruptedException {
		List<Integer> queue = new LinkedList<>();

		for (var i = 0; i < 10; i++) {
			queue.add(i + 1);
			System.out.println(i);
		}

//        for (Integer i : queue) {
//            System.out.println(i);
//        }

		var producer = new ProducerThread(queue, 10, 5000);
		var consumer = new ConsumerThread(queue, 500);

		producer.start();
		Thread.sleep(1000L);
		consumer.start();
	}
}
