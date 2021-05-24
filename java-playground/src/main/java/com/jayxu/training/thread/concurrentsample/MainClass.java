/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainClass {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

		for (var i = 0; i < 10; i++) {
			queue.add(i);
		}

		var producer = new ProducerThread(queue, 5000);
		var consumer = new ConsumeThread(queue, 500);

		producer.start();
		consumer.start();
	}
}
