/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.concurrentsample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainClass {
    static void main() {
        BlockingQueue<Integer> queue =
                IntStream.range(0, 10).boxed().collect(Collectors.toCollection(() -> new LinkedBlockingQueue<>(10)));

        var producer = new ProducerThread(queue, 5000);
        var consumer = new ConsumeThread(queue, 500);

        producer.start();
        consumer.start();
    }
}
