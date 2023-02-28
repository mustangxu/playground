/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.math;

import org.junit.jupiter.api.Test;
import org.slf4j.profiler.Profiler;

/**
 * @author xujiajing
 */
public class AddTest {
    @Test
    void testAdd() {
        var p = new Profiler("testAdd");

        p.start("100_000_000");
        for (var i = 0; i < 100_000_000; i++) {
        }

        p.start("1_000_000_000");
        for (var i = 0; i < 1_000_000_000; i++) {
        }

        p.stop();
        p.print();
    }
}
