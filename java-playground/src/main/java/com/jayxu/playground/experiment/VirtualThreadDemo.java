/**
 * Copyright(c) 2010-2023
 * All Rights Reserved
 */
package com.jayxu.playground.experiment;

import java.util.concurrent.TimeUnit;

import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayxu
 */
@Slf4j
public class VirtualThreadDemo {
    public void tryVirtualThread() {
        var watch = new StopWatch("virtual thread demo");
        watch.start("creation");
        final var N = 1_000;

        // virtual thread demo
        for (var i = 0; i < N; i++) {
            // virtual thread
            Thread.ofVirtual().name("virtual-thread-demo-" + i).start(() -> {
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException _) {
                    log.warn("{} interrupted", Thread.currentThread());
                }

                // string templates
                log.debug("{} ended", Thread.currentThread());
            });
        }

        log.info("{} virtual threads created", N);
        watch.stop();
        log.info(watch.toString());
    }
}
