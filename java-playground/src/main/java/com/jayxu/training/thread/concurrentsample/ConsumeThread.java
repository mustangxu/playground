/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.concurrent.BlockingQueue;

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
				Thread.sleep(this.interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
