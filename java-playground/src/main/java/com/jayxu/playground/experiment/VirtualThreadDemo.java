/**
 * Copyright(c) 2010-2023
 * All Rights Reserved
 */
package com.jayxu.playground.experiment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author jayxu
 */
@Slf4j
public class VirtualThreadDemo {
    public void tryVirtualThread() {
        var watch = new StopWatch("virtual thread demo");
        watch.start("creation");
        final var N = 1_000_000;

        // virtual thread demo
        for (var i = 0; i < N; i++) {
            // virtual thread
            Thread.ofVirtual().name("virtual-thread-demo-" + i).start(() -> {
                try {
                    Thread.sleep(30_000L);
                } catch (InterruptedException _) {
                    log.warn(STR."\{Thread.currentThread()} interrupted");
                }

                // string templates
                log.info(STR."\{Thread.currentThread()} ended");
            });
        }

        log.info(STR."\{N} virtual threads created");
        watch.stop();
        log.info(watch.toString());
    }
}
